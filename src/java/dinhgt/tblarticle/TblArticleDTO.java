/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblarticle;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class TblArticleDTO implements Serializable {

    private String postID;
    private String description;
    private String title;
    private Date date;
    private String email;
    private String status;
    private String image;

    public TblArticleDTO() {
    }

    public TblArticleDTO(String postID, String description, String title, Date date, String email, String status, String image) {
        this.postID = postID;
        this.description = description;
        this.title = title;
        this.date = date;
        this.email = email;
        this.status = status;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the postID
     */
    public String getPostID() {
        return postID;
    }

    /**
     * @param postID the postID to set
     */
    public void setPostID(String postID) {
        this.postID = postID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
