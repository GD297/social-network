/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.servlet;

import dinhgt.tblarticle.TblArticleDAO;
import dinhgt.tblarticle.TblArticleDTO;
import dinhgt.tblcomments.TblCommentsDAO;
import dinhgt.tblcomments.TblCommentsDTO;
import dinhgt.tblemotion.TblEmotionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
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
@WebServlet(name = "ViewDetailsServlet", urlPatterns = {"/ViewDetailsServlet"})
public class ViewDetailsServlet extends HttpServlet {

    private final String NOT_FOUND_PAGE = "notFoundPage.html";
    private final String VIEW_DETAILS = "viewDetailsPage.jsp";
    static final Logger LOGGER = Logger.getLogger(ViewDetailsServlet.class);

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
        String postid = request.getParameter("txtPostID");
        String url = NOT_FOUND_PAGE;
        try {
            TblArticleDAO articleDAO = new TblArticleDAO();
            TblArticleDTO articleDTO = articleDAO.loadDetailsArticle(postid);
            if (articleDTO != null) {
                TblEmotionDAO emotionDAO = new TblEmotionDAO();
                TblCommentsDAO commentsDAO = new TblCommentsDAO();
                int likeNumber = emotionDAO.loadEmotionLike(postid);
                int dislikeNumber = emotionDAO.loadEmotionDislike(postid);
                commentsDAO.loadCommentArticle(postid);
                List<TblCommentsDTO> listComment = commentsDAO.getListComment();
                request.setAttribute("LIKE_NUMBER", likeNumber);
                request.setAttribute("DISLIKE_NUMBERE", dislikeNumber);
                request.setAttribute("ARTICLE_DETAILS", articleDTO);
                request.setAttribute("LIST_COMMENTS", listComment);
            }
            url = VIEW_DETAILS;
        } catch (SQLException | NamingException ex) {
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
