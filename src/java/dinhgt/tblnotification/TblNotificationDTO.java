/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblnotification;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class TblNotificationDTO implements Serializable{
    private String email;
    private String postid;
    private Date date;
    private String type;

    public TblNotificationDTO() {
    }

    public TblNotificationDTO(String email, String postid, Date date, String type) {
        this.email = email;
        this.postid = postid;
        this.date = date;
        this.type = type;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the postid
     */
    public String getPostid() {
        return postid;
    }

    /**
     * @param postid the postid to set
     */
    public void setPostid(String postid) {
        this.postid = postid;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
}
