/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.dispatcher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class ServletDispatcher extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ServletDispatcher.class);
    private final String LOGIN_PAGE = "loginPage.html";
    private final String LOGIN_CONSTRUCTOR = "LoginServlet";
    private final String REGISTER_CONSTRUCTOR = "RegisterServlet";
    private final String LOGOUT_CONSTRUCTOR = "LogoutServlet";
    private final String VIEW_DETAILS = "ViewDetailsServlet";
    private final String SEARCH_CONSTRUCTOR = "SearchServlet";
    private final String POST_ARTICLE_CONSTRUCTOR = "PostArticleServlet";
    private final String COMMENT_ARTICLE_CONSTRUCTOR = "CommentServlet";
    private final String LOAD_CURRENT_USER_ARTICLE = "LoadCurrentUserArticle";
    private final String MAKE_EMOTION_CONSTRUCTOR = "MakeEmotionServlet";
    private final String GET_NOTIFICATION_CONSTRUCTOR = "NotificationServlet";
    private final String DELETE_COMMENT_CONSTRUCTOR = "DeleteServlet";
    private final String DELETE_ARTICLE_CONSTRUCTOR = "DeleteArticleServlet";
    private final String SEND_OTP_CONSTRUCTOR = "SendMailServlet";
    private final String VERIFY_ACCOUNT_CONSTRUCTOR = "VerifyRegisterServlet";

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
        String btn = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (btn == null) {
                
            } else if (btn.equals("Login")) {
                url = LOGIN_CONSTRUCTOR;
            } else if (btn.equals("Register")) {
                url = REGISTER_CONSTRUCTOR;
            } else if (btn.equals("Logout")) {
                url = LOGOUT_CONSTRUCTOR;
            } else if (btn.equals("Search")) {
                url = SEARCH_CONSTRUCTOR;
            } else if (btn.equals("ViewDetails")) {
                url = VIEW_DETAILS;
            } else if (btn.equals("Comments")) {
                url = COMMENT_ARTICLE_CONSTRUCTOR;
            } else if (btn.equals("Post")) {
                url = POST_ARTICLE_CONSTRUCTOR;
            } else if (btn.equals("LoadCurrent")) {
                url = LOAD_CURRENT_USER_ARTICLE;
            } else if (btn.equals("MakeEmotion")) {
                url = MAKE_EMOTION_CONSTRUCTOR;
            } else if (btn.equals("Notification")) {
                url = GET_NOTIFICATION_CONSTRUCTOR;
            } else if (btn.equals("DeleteComment")) {
                url = DELETE_COMMENT_CONSTRUCTOR;
            } else if (btn.equals("DeleteArticle")) {
                url = DELETE_ARTICLE_CONSTRUCTOR;
            } else if (btn.equals("Send OTP")) {
                url = SEND_OTP_CONSTRUCTOR;
            } else if (btn.equals("Submit")) {
                url = VERIFY_ACCOUNT_CONSTRUCTOR;
            }

        } catch (Exception ex) {
            LOGGER.info("Exception " + ex.getMessage());

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
