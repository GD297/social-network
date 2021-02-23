/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tbluser;

import dinhgt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class TblUserDAO implements Serializable {

    static final Logger LOGGER = Logger.getLogger(TblUserDAO.class);
    private List<TblUserDTO> listAccount;
    private TblUserDTO dto;

    public List<TblUserDTO> getListAccount() {
        return listAccount;
    }

    public TblUserDTO getDto() {
        return this.dto;
    }

    
    public boolean verifyAccount(String email) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "UPDATE tbl_Users "
                    + "SET Status=? "
                    + "WHERE Email=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Active");
            ps.setString(2, email);

            int countRowEffect = ps.executeUpdate();
            if (countRowEffect > 0) {
                return true;
            }
        } catch (NamingException ex) {
           
        } catch (SQLException ex) {
            
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
    
    public boolean checkLogin(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "Select Email,Name,Role,Status "
                    + "From tbl_Users "
                    + "Where Email=? And Password=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                String status = rs.getString("Status");
                String email = rs.getString("Email");
                String name = rs.getString("Name");
                String role = rs.getString("Role");
                dto = new TblUserDTO(email, name, role, status);
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

    public boolean registerAccount(String email, String name, String password, String role, String status) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHelpers.makeConnection();
            String query = "Insert Into tbl_Users "
                    + "Values (?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setString(5, status);

            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
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

}
