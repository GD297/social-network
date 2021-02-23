/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblnotification;

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
public class TblNotificationDAO implements Serializable {

    static final Logger LOGGER = Logger.getLogger(TblNotificationDAO.class);
    private List<TblNotificationDTO> listNoti;

    /**
     * @return the listNoti
     */
    public List<TblNotificationDTO> getListNoti() {
        return listNoti;
    }

    public void loadNotification(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblNotificationDTO notiDTO = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "SELECT tbl_Article.postID "
                    + ",tbl_Article.Email as ReceiveNoti"
                    + ",tbl_Notification.Email as whoAction "
                    + ",tbl_Notification.[Type] "
                    + ",tbl_Notification.[Date] "
                    + "FROM tbl_Article "
                    + "INNER JOIN tbl_Notification "
                    + "ON tbl_Notification.postID=tbl_Article.postID AND tbl_Article.Email=? AND tbl_Article.Email!=tbl_Notification.Email "
                    + "ORDER BY tbl_Notification.[Date] DESC";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                String postid = rs.getString("postID");
                String emailAction = rs.getString("whoAction");
                String type = rs.getString("Type");
                Date date = rs.getDate("Date");
                notiDTO = new TblNotificationDTO(emailAction, postid, date, type);
                if (this.listNoti == null) {
                    this.listNoti = new ArrayList<>();
                }
                listNoti.add(notiDTO);
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
    }

    public boolean commentNotification(String email, String postid, Date date, String type) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Notification "
                    + "VALUES(?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, postid);
            ps.setDate(3, date);
            ps.setString(4, type);
            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }

        }catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean emotionNotification(String email, String postid, Date date, String type) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_Notification "
                    + "VALUES(?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, postid);
            ps.setDate(3, date);
            ps.setString(4, type);
            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }

        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean changeEmotionNotification(String email, String postid, Date date, String type) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Notification "
                    + "SET Type=?, Date=? "
                    + "WHERE postID=? AND Email=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, type);
            ps.setDate(2, date);
            ps.setString(3, postid);
            ps.setString(4, email);
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

}
