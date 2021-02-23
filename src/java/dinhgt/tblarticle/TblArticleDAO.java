/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblarticle;

import dinhgt.utils.DBHelpers;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class TblArticleDAO implements Serializable {

    private List<TblArticleDTO> listDTO;
    static final Logger LOGGER = Logger.getLogger(TblArticleDAO.class);

    public List<TblArticleDTO> getListDTO() {
        return listDTO;
    }

    public boolean deleteArticle(String postid) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Article "
                    + "SET Status=? "
                    + "WHERE postID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Delete");
            ps.setString(2, postid);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return false;
    }

    public void searchArticle(String searchValue, int startRecord, int toRecord) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String imgBase64 = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "Select postID, Descripetion, Title, Date, Email, Status, Image "
                    + "From tbl_Article "
                    + "Where Descripetion Like ? AND Status!=? "
                    + "ORDER BY Date DESC"
                    + " OFFSET " + startRecord + " ROWS "
                    + "FETCH NEXT 20 ROWS ONLY";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchValue + "%");
            ps.setString(2, "Delete");
            rs = ps.executeQuery();
            while (rs.next()) {
                String postid = rs.getString("postID");
                String description = rs.getNString("Descripetion");
                String title = rs.getNString("Title");
                Date date = rs.getDate("Date");
                String Email = rs.getString("Email");
                String status = rs.getString("Status");
                byte[] image = rs.getBytes("Image");
                if(image != null){
                    imgBase64 = Base64.getEncoder().encodeToString(image);
                }
                TblArticleDTO dto = new TblArticleDTO(postid, description, title, date, Email, status, imgBase64);
                if (this.listDTO == null) {
                    this.listDTO = new ArrayList<>();
                }
                listDTO.add(dto);
            }
        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
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

    public int countArticleSearch(String searchValue) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBHelpers.makeConnection();
            String query = "Select COUNT(*) As Total "
                    + "From tbl_Article "
                    + "Where Descripetion Like ?  AND Status!=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchValue + "%");
            ps.setString(2, "Delete");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("Total");
            }
            return count;
        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
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
        return count;
    }

    public TblArticleDTO loadDetailsArticle(String postid) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblArticleDTO dtoEmotion = null;
        String imgBase64 = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT postID, Descripetion, Title, Date, Email, Status, Image "
                    + "FROM tbl_Article "
                    + "WHERE postID=? AND Status!=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, postid);
            ps.setString(2, "Delete");
            rs = ps.executeQuery();
            if (rs.next()) {
                String description = rs.getNString("Descripetion");
                String title = rs.getNString("Title");
                Date date = rs.getDate("Date");
                String email = rs.getString("Email");
                String status = rs.getString("Status");
                byte[] image = rs.getBytes("Image");
                if(image != null){
                    imgBase64 = Base64.getEncoder().encodeToString(image);
                }
                dtoEmotion = new TblArticleDTO(postid, description, title, date, email, status, imgBase64);
            }
            return dtoEmotion;

        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
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

        return dtoEmotion;
    }

    public boolean storedArticle(String description, String title, Date date, String status, InputStream image, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Article "
                    + "VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setNString(1, description);
            ps.setNString(2, title);
            ps.setDate(3, date);
            ps.setString(4, email);
            ps.setString(5, status);
            ps.setBinaryStream(6, image);

            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }

        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
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

    public void loadCurrentUserArticle(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String imgBase64 = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "Select postID, Descripetion, Title, Date, Email, Status, Image "
                    + "From tbl_Article "
                    + "Where Email=? AND Status!=? "
                    + "ORDER BY postID DESC";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, "Delete");
            rs = ps.executeQuery();
            while (rs.next()) {
                String postid = rs.getString("postID");
                String description = rs.getNString("Descripetion");
                String title = rs.getNString("Title");
                Date date = rs.getDate("Date");
                String Email = rs.getString("Email");
                String status = rs.getString("Status");
               byte[] image = rs.getBytes("Image");
                if(image != null){
                    imgBase64 = Base64.getEncoder().encodeToString(image);
                }
                TblArticleDTO dto = new TblArticleDTO(postid, description, title, date, Email, status, imgBase64);
                if (this.listDTO == null) {
                    this.listDTO = new ArrayList<>();
                }
                listDTO.add(dto);
            }
        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
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
