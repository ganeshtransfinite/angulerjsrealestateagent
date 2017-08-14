/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.agent;

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

import pms.city.CityForm;
import pms.modeoperation.ModeoperationForm;
import pms.propertylocality.PropertylocalityForm;
import admin.user.UserForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Agent")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "agent")
public class AgentForm implements Serializable {

    @Column(name = "AGENTID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int agentid;
    @Column(name = "NAME", length = 4000)
    String name;
    @Column(name = "MOBILE", length = 4000)
    String mobile;
    @Column(name = "EMAIL", length = 4000)
    String email;
    @Column(name = "COMPANYNAME", length = 4000)
    String companyname;
    @Column(name = "ADDRESS", length = 4000)
    String address;
    @Transient
    int selectcityid;
    @OneToOne
    @JoinColumn(name = "CITYID", referencedColumnName = "CITYID")

    CityForm cityid;
    @Transient
    int[] selectpropertylocalityid;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_AGENT_PROPERTYLOCALITYID",
            joinColumns
            = @JoinColumn(name = "AGENTID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYLOCALITYID")
    )
    Collection<PropertylocalityForm> propertylocalityid;
    @Transient
    int[] selectmodeoperationid;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_AGENT_MODEOPERATIONID",
            joinColumns
            = @JoinColumn(name = "AGENTID"), inverseJoinColumns = @JoinColumn(name = "MODEOPERATIONID")
    )
    Collection<ModeoperationForm> modeoperationid;
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

    @Column(name = "REMARK", length = 4000)
    private String remark;

    @Column(name = "CONTACT1", length = 100)
    private String contact1;
    @Column(name = "CONTACT2", length = 100)
    private String contact2;
    @Column(name = "CONTACT3", length = 100)
    private String contact3;

    public AgentForm() {
    }

    public AgentForm(AgentForm obj) {
        this.agentid = obj.agentid;
        this.name = obj.name;
        this.mobile = obj.mobile;
        this.email = obj.email;
        this.companyname = obj.companyname;
        this.address = obj.address;
        this.cityid = obj.cityid;
        this.propertylocalityid = obj.propertylocalityid;
        this.modeoperationid = obj.modeoperationid;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.userid = obj.userid;
        this.recordorder = obj.recordorder;
        this.remark = obj.remark;
        this.contact1 = obj.contact1;
        this.contact2 = obj.contact2;
        this.contact3 = obj.contact3;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCityid(CityForm cityid) {
        this.cityid = cityid;
    }

    public void setSelectcityid(int cityid) {
        this.selectcityid = cityid;
    }

    public void setPropertylocalityid(Collection<PropertylocalityForm> propertylocalityid) {
        this.propertylocalityid = propertylocalityid;
    }

    public void setSelectpropertylocalityid(int[] propertylocalityid) {
        this.selectpropertylocalityid = propertylocalityid;
    }

    public void setModeoperationid(Collection<ModeoperationForm> modeoperationid) {
        this.modeoperationid = modeoperationid;
    }

    public void setSelectmodeoperationid(int[] modeoperationid) {
        this.selectmodeoperationid = modeoperationid;
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

    public int getAgentid() {
        return agentid;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getAddress() {
        return address;
    }

    public CityForm getCityid() {
        return cityid;
    }

    public int getSelectcityid() {
        return selectcityid;
    }

    public Collection<PropertylocalityForm> getPropertylocalityid() {
        return propertylocalityid;
    }

    public int[] getSelectpropertylocalityid() {
        return selectpropertylocalityid;
    }

    public Collection<ModeoperationForm> getModeoperationid() {
        return modeoperationid;
    }

    public int[] getSelectmodeoperationid() {
        return selectmodeoperationid;
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
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the contact1
     */
    public String getContact1() {
        return contact1;
    }

    /**
     * @param contact1 the contact1 to set
     */
    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    /**
     * @return the contact2
     */
    public String getContact2() {
        return contact2;
    }

    /**
     * @param contact2 the contact2 to set
     */
    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    /**
     * @return the contact3
     */
    public String getContact3() {
        return contact3;
    }

    /**
     * @param contact3 the contact3 to set
     */
    public void setContact3(String contact3) {
        this.contact3 = contact3;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
}
