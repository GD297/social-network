/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tblemotion;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblEmotionDTO implements Serializable{
    
    private String postid;
    private String email;
    private String Emotion;

    public TblEmotionDTO() {
    }

    public TblEmotionDTO(String postid, String email, String Emotion) {
        this.postid = postid;
        this.email = email;
        this.Emotion = Emotion;
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
     * @return the Emotion
     */
    public String getEmotion() {
        return Emotion;
    }

    /**
     * @param Emotion the Emotion to set
     */
    public void setEmotion(String Emotion) {
        this.Emotion = Emotion;
    }
    
}
