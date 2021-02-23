/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblcomments;

import dinhgt.tblarticle.TblArticleDTO;
import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class TblCommentsDAO implements Serializable {

     static final Logger LOGGER = Logger.getLogger(TblCommentsDAO.class);
    private List<TblCommentsDTO> listComment;

    public List<TblCommentsDTO> getListComment() {
        return listComment;
    }

    public boolean deleteComment(String email, String postid, String content, String date) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "DELETE FROM tbl_Comments "
                    + "WHERE postID=? AND Email=? AND Content=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, postid);
            ps.setString(2, email);
            ps.setString(3, content);
            int countEffect = ps.executeUpdate();
            System.out.println(countEffect);
            if (countEffect > 0) {
                return true;
            }

        } catch (SQLException ex) {
            LOGGER.info("SQLException "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException "+ex.getMessage());
        } finally {

            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }

        }

        return false;
    }

    public boolean newCommentArticle(String commentContent, String email, String postid, Date dateComment) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Comments "
                    + "VALUES (?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, postid);
            ps.setString(2, email);
            ps.setNString(3, commentContent);
            ps.setDate(4, dateComment);
            int countEffect = ps.executeUpdate();

            if (countEffect > 0) {
                return true;
            }

        }catch (SQLException ex) {
            LOGGER.info("SQLException "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException "+ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }

        }
        return false;
    }

    public void loadCommentArticle(String postid) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT postID, Date, Email, Content"
                    + " FROM tbl_Comments "
                    + "WHERE postID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, postid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("Date");
                String Email = rs.getString("Email");
                String content = rs.getNString("Content");
                TblCommentsDTO commentDTO = new TblCommentsDTO(postid, Email, content, date);
                if (this.listComment == null) {
                    this.listComment = new ArrayList<>();
                }
                listComment.add(commentDTO);
            }

        } catch (SQLException ex) {
            LOGGER.info("SQLException "+ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException "+ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }

        }

    }

}
