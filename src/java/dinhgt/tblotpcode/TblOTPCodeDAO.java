/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblotpcode;

import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class TblOTPCodeDAO implements Serializable {

    static final Logger LOGGER = Logger.getLogger(TblOTPCodeDAO.class);

    public boolean storeOTPCode(String otp, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "INSERT INTO tbl_OTPCode "
                    + "VALUES (?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, otp);
            ps.setString(3, "Not Yet");

            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
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

    public boolean findOTPCode(String otp, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_OTPCode "
                    + "SET Status=? "
                    + "WHERE Email=? AND OTP=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Active");
            ps.setString(2, email);
            ps.setString(3, otp);

            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }
        } catch (NamingException ex) {
            LOGGER.info("NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SQLException " + ex.getMessage());
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

}
