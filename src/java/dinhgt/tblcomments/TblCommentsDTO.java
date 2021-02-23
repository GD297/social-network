/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblcomments;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class TblCommentsDTO implements Serializable{
    private String postid;
    private String email;
    private String content;
    private Date date;

    public TblCommentsDTO() {
    }

    public TblCommentsDTO(String postid, String email, String content, Date date) {
        this.postid = postid;
        this.email = email;
        this.content = content;
        this.date = date;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
}
