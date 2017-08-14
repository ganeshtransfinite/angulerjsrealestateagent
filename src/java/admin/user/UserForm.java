/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.user;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import admin.role.RoleForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "User")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "user")
public class UserForm implements Serializable {

    @Column(name = "USERID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int userid;
    @Column(name = "EMAIL", length = 1000)
    String email;

    @Column(name = "PASSWORD", length = 1000)
    String password;
    @Transient
    String confirmpassword;
    @Transient
    int selectroleid;
    @OneToOne
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")

    RoleForm roleid;
    @Column(name = "ACTIVE", length = 1)
    String active;
    @Column(name = "CREATEDDATE")
    Date createddate;
    @Column(name = "MODIFYDDATE")
    Date modifyddate;
    @Column(name = "FULLNAME", length = 1000)
    String fullname;
    @Column(name = "RECORDORDER")
    int recordorder;
    @Transient
    private String option;
    @Transient
    private String oldpwd;

    public UserForm() {
    }

    public UserForm(UserForm obj) {
        this.userid = obj.userid;
        this.email = obj.email;
       
        this.roleid = obj.roleid;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.fullname = obj.fullname;
        this.recordorder = obj.recordorder;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public void setRoleid(RoleForm roleid) {
        this.roleid = roleid;
    }

    public void setSelectroleid(int roleid) {
        this.selectroleid = roleid;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public void setModifyddate(Date modifyddate) {
        this.modifyddate = modifyddate;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setRecordorder(int recordorder) {
        this.recordorder = recordorder;
    }

    public int getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public RoleForm getRoleid() {
        return roleid;
    }

    public int getSelectroleid() {
        return selectroleid;
    }

    public String getActive() {
        return active;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public Date getModifyddate() {
        return modifyddate;
    }

    public String getFullname() {
        return fullname;
    }

    public int getRecordorder() {
        return recordorder;
    }

    /**
     * @return the option
     */
    public String getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * @return the oldpwd
     */
    public String getOldpwd() {
        return oldpwd;
    }

    /**
     * @param oldpwd the oldpwd to set
     */
    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }
}
