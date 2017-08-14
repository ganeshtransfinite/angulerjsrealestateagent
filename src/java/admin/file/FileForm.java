/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.file;

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
import admin.user.UserForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "File")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "file")
public class FileForm implements Serializable {

    @Column(name = "FILEID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int fileid;
    @Column(name = "FILENAME", length = 1000)
    String filename;
    @Column(name = "CONENTTYPE", length = 1000)
    String conenttype;
    @Transient
    int selectcontent;
    @Column(name = "CONTENT", columnDefinition = "LONGBLOB")
    byte[] content;
    
     @Column(name = "CONTENTICON", columnDefinition = "LONGBLOB")
    private byte[] contenticon;
     
    @Column(name = "TYPE", length = 1000)
    String type;
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

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setConenttype(String conenttype) {
        this.conenttype = conenttype;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setSelectcontent(int content) {
        this.selectcontent = content;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getFileid() {
        return fileid;
    }

    public String getFilename() {
        return filename;
    }

    public String getConenttype() {
        return conenttype;
    }

    public byte[] getContent() {
        return content;
    }

    public int getSelectcontent() {
        return selectcontent;
    }

    public String getType() {
        return type;
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

    /**
     * @return the contenticon
     */
    public byte[] getContenticon() {
        return contenticon;
    }

    /**
     * @param contenticon the contenticon to set
     */
    public void setContenticon(byte[] contenticon) {
        this.contenticon = contenticon;
    }
}
