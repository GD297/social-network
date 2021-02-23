/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.tbluser;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblUserCreateNewError implements Serializable{
    private String emailLengthErr;
    private String nameLengthErr;
    private String passwordLengthErr;
    private String emailDuplicate;

    /**
     * @return the emailLengthErr
     */
    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    /**
     * @param emailLengthErr the emailLengthErr to set
     */
    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    /**
     * @return the nameLengthErr
     */
    public String getNameLengthErr() {
        return nameLengthErr;
    }

    /**
     * @param nameLengthErr the nameLengthErr to set
     */
    public void setNameLengthErr(String nameLengthErr) {
        this.nameLengthErr = nameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the emailDuplicate
     */
    public String getEmailDuplicate() {
        return emailDuplicate;
    }

    /**
     * @param emailDuplicate the emailDuplicate to set
     */
    public void setEmailDuplicate(String emailDuplicate) {
        this.emailDuplicate = emailDuplicate;
    }
    
    
    
    
}
