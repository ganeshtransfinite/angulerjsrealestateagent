/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.datarecovery;

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
import javax.persistence.ForeignKey;
import javax.persistence.Transient;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.ManyToMany;

import admin.user.UserForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Datarecovery")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "datarecovery")
public class DatarecoveryForm implements Serializable {

    @Column(name = "DATARECOVERYID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int datarecoveryid;
    @Column(name = "TABLENAME", length = 1000)
    String tablename;
    @Transient
    int selectcontent;
    @Column(name = "CONTENT", columnDefinition = "LONGBLOB")
    byte[] content;
    @Transient
    int selectuserid;
    @OneToOne
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")

    UserForm userid;
    @Column(name = "ACTIVE", length = 1)
    String active;
    @Column(name = "CREATEDDATE", length = 1)
    Date createddate;
    @Column(name = "MODIFYDDATE", length = 1)
    Date modifyddate;
    @Column(name = "RECORDORDER")
    int recordorder;

    public DatarecoveryForm() {
    }

    public DatarecoveryForm(DatarecoveryForm obj) {
        this.datarecoveryid = obj.datarecoveryid;
        this.tablename = obj.tablename;
        this.content = obj.content;
        this.userid = obj.userid;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.recordorder = obj.recordorder;
    }

    public void setDatarecoveryid(int datarecoveryid) {
        this.datarecoveryid = datarecoveryid;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setSelectcontent(int content) {
        this.selectcontent = content;
    }

    public void setUserid(UserForm userid) {
        this.userid = userid;
    }

    public void setSelectuserid(int userid) {
        this.selectuserid = userid;
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

    public void setRecordorder(int recordorder) {
        this.recordorder = recordorder;
    }

    public int getDatarecoveryid() {
        return datarecoveryid;
    }

    public String getTablename() {
        return tablename;
    }

    public byte[] getContent() {
        return content;
    }

    public int getSelectcontent() {
        return selectcontent;
    }

    public UserForm getUserid() {
        return userid;
    }

    public int getSelectuserid() {
        return selectuserid;
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

    public int getRecordorder() {
        return recordorder;
    }
}
