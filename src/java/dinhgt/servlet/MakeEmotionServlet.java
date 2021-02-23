/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblemotion.TblEmotionDAO;
import dinhgt.tblemotion.TblEmotionDTO;
import dinhgt.tblnotification.TblNotificationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "MakeEmotionServlet", urlPatterns = {"/MakeEmotionServlet"})
public class MakeEmotionServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage.html";
    private final String VIEW_DETAILS_CONSTRUCTOR = "ViewDetailsServlet";
    static final Logger LOGGER = Logger.getLogger(MakeEmotionServlet.class);

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
        String typeEmotion = request.getParameter("typeEmotion");
        String url = LOGIN_PAGE;
        String postid = request.getParameter("txtPostID");
        boolean alreadyEmotion;
        String typeNoti = null;
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            TblEmotionDAO emotionDAO = new TblEmotionDAO();
            TblNotificationDAO notiDAO = new TblNotificationDAO();
            TblEmotionDTO emotionDTO = emotionDAO.checkAlreadyEmotion(postid, email);
            if (emotionDTO != null) {
                if (emotionDTO.getEmotion() == null) {
                    alreadyEmotion = emotionDAO.changeEmotion(postid, email, typeEmotion);
                    if (alreadyEmotion) {
                        if (typeEmotion.equals("1")) {
                            typeNoti = "Like";
                        }
                        if (typeEmotion.equals("0")) {
                            typeNoti = "Dislike";
                        }
                        boolean result = notiDAO.changeEmotionNotification(email, postid, date, typeNoti);
                        if (result) {
                            url = VIEW_DETAILS_CONSTRUCTOR;
                        }
                    }
                } else {
                    if (emotionDTO.getEmotion().equals(typeEmotion)) {
                        alreadyEmotion = emotionDAO.setAlreadyEmotion(postid, email, typeEmotion);
                        if (alreadyEmotion) {
                            if (typeEmotion.equals("1")) {
                                typeNoti = "Like";
                            }
                            if (typeEmotion.equals("0")) {
                                typeNoti = "Dislike";
                            }

                            boolean result = notiDAO.changeEmotionNotification(email, postid, date, typeNoti);
                            if (result) {
                                url = VIEW_DETAILS_CONSTRUCTOR;
                            }

                        }
                    } else {
                        alreadyEmotion = emotionDAO.changeEmotion(postid, email, typeEmotion);
                        if (alreadyEmotion) {
                            if (typeEmotion.equals("1")) {
                                typeNoti = "Like";
                            }
                            if (typeEmotion.equals("0")) {
                                typeNoti = "Dislike";
                            }

                            boolean result = notiDAO.changeEmotionNotification(email, postid, date, typeNoti);
                            if (result) {
                                url = VIEW_DETAILS_CONSTRUCTOR;
                            }
                        }
                    }

                }
            } else {
                alreadyEmotion = emotionDAO.insertEmotion(postid, email, typeEmotion);
                if (alreadyEmotion) {

                    if (typeEmotion.equals("1")) {
                        typeNoti = "Like";
                    }
                    if (typeEmotion.equals("0")) {
                        typeNoti = "Dislike";
                    }

                    boolean result = notiDAO.emotionNotification(email, postid, date, typeNoti);
                    if (result) {
                        url = VIEW_DETAILS_CONSTRUCTOR;
                    }

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
