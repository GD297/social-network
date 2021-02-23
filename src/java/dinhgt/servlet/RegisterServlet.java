/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tbluser.TblUserCreateNewError;
import dinhgt.tbluser.TblUserDAO;
import dinhgt.utils.HasingData;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String REGISTER_COMPLETE = "activatedPage.jsp";
    private final String REGISTER_FAIL = "registerPage.jsp";
    static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String name = request.getParameter("txtName");
        String role = request.getParameter("txtRole");
        String status = request.getParameter("txtStatus");
        String url = REGISTER_FAIL;
        boolean foundErr = false;
        TblUserCreateNewError error = new TblUserCreateNewError();
        try {
            if (email.trim().length() < 20) {
                foundErr = true;
                error.setEmailLengthErr("Email not exits!! (more than 20 character)");
            }
            if (password.length() < 6) {
                foundErr = true;
                error.setPasswordLengthErr("Password to weak!! (more than 6 character)");
            }
            if (name.length() < 2) {
                foundErr = true;
                error.setNameLengthErr("Please enter your full name!!!");
            }
            if (foundErr) {
                request.setAttribute("ERROR_HAPPEN", error);
            } else {
                String hashpassword = HasingData.hashSHA256(password);
                TblUserDAO dao = new TblUserDAO();
                boolean result = dao.registerAccount(email, name, hashpassword, role, status);
                if (result) {
                    url = REGISTER_COMPLETE;
                }
            }
        } catch (SQLException ex) {
            String msgErr = ex.getMessage();
            if (msgErr.contains("duplicate")) {
                error.setEmailDuplicate("Email dose exits!!!");
                request.setAttribute("ERROR_HAPPEN", error);
                LOGGER.info("Error duplicate Email: " + ex.getMessage());
            } else {
                LOGGER.info("SQLException " + ex.getMessage());
            }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {

        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {

        }
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
