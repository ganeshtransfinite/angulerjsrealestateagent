/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.operation;

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
import admin.user.UserForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Operation")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "operation")
public class OperationForm implements Serializable {

    @Column(name = "OPERATIONID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int operationid;
    @Column(name = "NAME", length = 4000)
    String name;
    @Column(name = "FUNNAME", length = 4000)
    String funname;
    @Column(name = "URL", length = 4000)
    String url;
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

    public OperationForm() {
    }

    public OperationForm(OperationForm obj) {
        this.operationid = obj.operationid;
        this.name = obj.name;
        this.funname = obj.funname;
        this.url = obj.url;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.userid = obj.userid;
        this.recordorder = obj.recordorder;
    }

    public void setOperationid(int operationid) {
        this.operationid = operationid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getOperationid() {
        return operationid;
    }

    public String getName() {
        return name;
    }

    public String getFunname() {
        return funname;
    }

    public String getUrl() {
        return url;
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
}
