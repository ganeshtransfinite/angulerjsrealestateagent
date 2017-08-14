/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.rolepermission;

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
import admin.user.UserForm;
import admin.operation.OperationForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Rolepermission")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "rolepermission")
public class RolepermissionForm implements Serializable {

    @Column(name = "ROLEPERMISSIONID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int rolepermissionid;
    @Column(name = "PFUNCTION")
    private boolean pfunction;
    @Transient
    int selectroleid;
    @OneToOne
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID")

    RoleForm roleid;
    @Transient
    int selectoperationid;
    @OneToOne
    @JoinColumn(name = "OPERATIONID", referencedColumnName = "OPERATIONID")

    OperationForm operationid;
    @Column(name = "ACTIVE", length = 1)
    String active;
    @Column(name = "CREATEDDATE")
    Date createddate;
    @Column(name = "MODIFYDDATE")
    Date modifyddate;
    @Transient
    int selectuserid;
    @OneToOne
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")

    UserForm userid;
    @Column(name = "RECORDORDER")
    int recordorder;

    public RolepermissionForm() {
    }

    public RolepermissionForm(RolepermissionForm obj) {
        this.rolepermissionid = obj.rolepermissionid;
        this.pfunction = obj.pfunction;
        this.roleid = obj.roleid;
        this.operationid = obj.operationid;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.userid = obj.userid;
        this.recordorder = obj.recordorder;
    }

    public void setRolepermissionid(int rolepermissionid) {
        this.rolepermissionid = rolepermissionid;
    }

    public void setPfunction(boolean pfunction) {
        this.pfunction = pfunction;
    }

    public void setRoleid(RoleForm roleid) {
        this.roleid = roleid;
    }

    public void setSelectroleid(int roleid) {
        this.selectroleid = roleid;
    }

    public void setOperationid(OperationForm operationid) {
        this.operationid = operationid;
    }

    public void setSelectoperationid(int operationid) {
        this.selectoperationid = operationid;
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

    public void setUserid(UserForm userid) {
        this.userid = userid;
    }

    public void setSelectuserid(int userid) {
        this.selectuserid = userid;
    }

    public void setRecordorder(int recordorder) {
        this.recordorder = recordorder;
    }

    public int getRolepermissionid() {
        return rolepermissionid;
    }

    public boolean getPfunction() {
        return isPfunction();
    }

    public RoleForm getRoleid() {
        return roleid;
    }

    public int getSelectroleid() {
        return selectroleid;
    }

    public OperationForm getOperationid() {
        return operationid;
    }

    public int getSelectoperationid() {
        return selectoperationid;
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

    public UserForm getUserid() {
        return userid;
    }

    public int getSelectuserid() {
        return selectuserid;
    }

    public int getRecordorder() {
        return recordorder;
    }

    /**
     * @return the pfunction
     */
    public boolean isPfunction() {
        return pfunction;
    }
}
