/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tbluser.TblUserDAO;
import dinhgt.tbluser.TblUserDTO;
import dinhgt.utils.HasingData;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String ACTIVATED_ACCOUNT = "Active";
    private final String HOME_PAGE = "homePage.jsp";
    private final String INVALID_PAGE = "invalidLogin.html";
    private final String ACTIVE_PAGE = "activatedPage.jsp";
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = INVALID_PAGE;
        try {
            String hashedPassword = HasingData.hashSHA256(password);
            TblUserDAO dao = new TblUserDAO();
            boolean result = dao.checkLogin(email, hashedPassword);
            if (result) {
                TblUserDTO dto = dao.getDto();
                if (dto.getStatus().equals(ACTIVATED_ACCOUNT)) {
                    HttpSession session = request.getSession();
                    Cookie cookieCurrentUser = new Cookie("USERNAME", dto.getEmail());
                    String name = dto.getName();
                    response.addCookie(cookieCurrentUser);
                    session.setAttribute("USERNAME", name);
                    session.setAttribute("EMAIL", dto.getEmail());
                    url = HOME_PAGE;
                } else {
                    url = ACTIVE_PAGE+"?txtEmail="+email+"&txtPassword="+password;
                }//end if active is not Acticated
            }
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.info("NoSuchAlgorithmException " + ex.getMessage());
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
