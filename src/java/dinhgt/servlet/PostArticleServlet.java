/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblarticle.FoundErrArticle;
import dinhgt.tblarticle.TblArticleDAO;
import dinhgt.utils.OutputImageToFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PostArticleServlet", urlPatterns = {"/PostArticleServlet"})
@MultipartConfig(location = "/images")
public class PostArticleServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage.html";
    private final String LOAD_ARTICLE_CONSTRUCTOR = "LoadCurrentUserArticle";
    private final String POST_ARTICLE_PAGE = "postArticlePage.jsp";
    static final Logger LOGGER = Logger.getLogger(PostArticleServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = request.getParameter("txtTitle");
        String description = request.getParameter("txtDescription");
        Part filePart = request.getPart("Image");
        String status = request.getParameter("txtStatus");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream imageContent = filePart.getInputStream();
        String url = LOGIN_PAGE;
        boolean foundErr = false;
        try {
            FoundErrArticle errors = new FoundErrArticle();
            if (title.trim().length() < 10) {
                errors.setTitleErrLength("Title of article cannot empty!!!!");
                foundErr = true;
            }
            if (description.trim().length() < 10) {
                errors.setDesErrLength("Description to short!!!");
                foundErr = true;
            }
            if (foundErr) {
                request.setAttribute("ERROR_HAPPEN", errors);
                url = POST_ARTICLE_PAGE;

            } else {
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("EMAIL");
                long currentTime = System.currentTimeMillis();
                Date date = new Date(currentTime);
                TblArticleDAO articleDAO = new TblArticleDAO();
                boolean result = articleDAO.storedArticle(description, title, date, status, imageContent, email);
                if (result) {
                    String fixPath = request.getServletContext().getRealPath("/");
                    String fixPathProjet = (request.getServletContext().getRealPath("/") + session.getAttribute("USERNAME")).replace("\\build\\web", "\\web\\WEB-INF\\images");
                    File pathImage = new File(fixPathProjet);
                    System.out.println("pathImage: "+pathImage.getAbsolutePath());
                    OutputImageToFile.copyInputStreamToFile(imageContent, pathImage, fileName);
                    String fixPathWebapps = fixPath + "images/" + session.getAttribute("USERNAME");
                    pathImage = new File(fixPathWebapps);
                    OutputImageToFile.copyInputStreamToFile(imageContent, pathImage, fileName);
                    url = LOAD_ARTICLE_CONSTRUCTOR;
                }
            }
        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
