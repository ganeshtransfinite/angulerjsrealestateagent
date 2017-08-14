/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import admin.user.UserForm;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class UserSession {

    public String JSESSIONID;
    public int userid;
    private String rolename;
    public String username;
    private Date time = new Date();
    private UserForm user;

    public HashMap<String, HashMap<String, Boolean>> role = new HashMap();

    public UserSession(String JSESSIONID, int userid) {
        this.JSESSIONID = JSESSIONID;
        this.userid = userid;
    }

    public UserSession(String JSESSIONID, int userid, String username, String rolename) {
        this.JSESSIONID = JSESSIONID;
        this.userid = userid;
        this.rolename = rolename;
        this.username = username;
    }

    
    public UserSession(String JSESSIONID, int userid, String username) {
        this.JSESSIONID = JSESSIONID;
        this.userid = userid;
        this.username = username;
    }

    /**
     * @return the role
     */
    public  HashMap<String, HashMap<String, Boolean>> getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(HashMap role) {
        this.role = role;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename the rolename to set
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return the user
     */
    public UserForm getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserForm user) {
        this.user = user;
    }

}
