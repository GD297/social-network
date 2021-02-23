/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblarticle.TblArticleDAO;
import dinhgt.tblarticle.TblArticleDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoadCurrentUserArticle", urlPatterns = {"/LoadCurrentUserArticle"})
public class LoadCurrentUserArticle extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage.html";
    private final String USER_WALL_PAGE = "userWallPage.jsp";
    static final Logger LOGGER = Logger.getLogger(LoadCurrentUserArticle.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        PrintWriter out = response.getWriter();
        
        
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            if (!(email.trim().isEmpty())) {
                TblArticleDAO articleDAO = new TblArticleDAO();
                articleDAO.loadCurrentUserArticle(email);
                List<TblArticleDTO> listArticle = articleDAO.getListDTO();
                String fixPathProjet = (request.getServletContext().getRealPath("/") + session.getAttribute("USERNAME")).replace("\\build\\web", "\\web\\WEB-INF\\images");
                fixPathProjet = fixPathProjet.replace("\\", "\\");
                request.setAttribute("LIST_ARTICLE", listArticle);
                request.setAttribute("FILE_PATH", fixPathProjet);
                url = USER_WALL_PAGE;
            }

        } catch (SQLException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
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
