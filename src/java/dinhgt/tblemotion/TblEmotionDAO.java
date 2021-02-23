/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblemotion;

import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class TblEmotionDAO implements Serializable {

    static final Logger LOGGER = Logger.getLogger(TblEmotionDAO.class);

    public boolean checkLikeEmotion(String postid, String email, String typeEmotion) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT postID, Emotion, Email "
                    + "FROM tbl_Emotion "
                    + "WHERE postID=? AND Email=? AND Emotion=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, postid);
            ps.setString(2, email);
            ps.setString(3, typeEmotion);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean emotion = rs.getBoolean("Emotion");
                if (emotion) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean insertEmotion(String postid, String email, String typeEmotion) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Emotion "
                    + "VALUES (?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, postid);
            ps.setString(2, email);
            ps.setString(3, typeEmotion);
            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }

        }  catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean setAlreadyEmotion(String postid, String email, String typeEmotion) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Emotion "
                    + "SET Emotion=? "
                    + "WHERE postID=? AND Email=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, null);
            ps.setString(2, postid);
            ps.setString(3, email);
            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }

        }  catch (NamingException ex) {
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

    public boolean changeEmotion(String postid, String email, String typeEmotion) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Emotion "
                    + "SET Emotion=? "
                    + "WHERE postID=? AND Email=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, typeEmotion);
            ps.setString(2, postid);
            ps.setString(3, email);
            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }

        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public TblEmotionDTO checkAlreadyEmotion(String postid, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblEmotionDTO emotionDTO = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT postID, Email, Emotion "
                    + "FROM tbl_Emotion "
                    + "WHERE postID=? AND Email=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, postid);
            ps.setString(2, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                emotionDTO = new TblEmotionDTO(rs.getString("postID"), rs.getString("Email"), rs.getString("Emotion"));
                return emotionDTO;
            }
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return emotionDTO;
    }

    public int loadEmotionLike(String postID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int numberOfLike = 0;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT COUNT(*) as [Like] "
                    + "FROM tbl_Emotion "
                    + "WHERE postID=? AND Emotion=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, postID);
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            if (rs.next()) {
                numberOfLike = rs.getInt("Like");
            }
            return numberOfLike;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int loadEmotionDislike(String postID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int numberOfDislike = 0;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT COUNT(*) as [Like] "
                    + "FROM tbl_Emotion "
                    + "WHERE postID=? AND Emotion=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, postID);
            ps.setBoolean(2, false);
            rs = ps.executeQuery();
            if (rs.next()) {
                numberOfDislike = rs.getInt("Like");
            }
            return numberOfDislike;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
