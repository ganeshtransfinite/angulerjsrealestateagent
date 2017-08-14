/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.customer;

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
import pms.property.PropertyForm;
import pms.occupation.OccupationForm;
import pms.gender.GenderForm;
import pms.city.CityForm;
import pms.customertype.CustomertypeForm;
import admin.user.UserForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Customer")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "customer")
public class CustomerForm implements Serializable {

    @Column(name = "CUSTOMERID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int customerid;
    @Column(name = "FULLNAME", length = 4000)
    String fullname;

    @Column(name = "CONTACT1", length = 100)
    private String contact1;
    @Column(name = "CONTACT2", length = 100)
    private String contact2;
    @Column(name = "CONTACT3", length = 100)
    private String contact3;

    @Transient
    int selectcustomertypeid;
    @OneToOne
    @JoinColumn(name = "CUSTOMERTYPEID", referencedColumnName = "CUSTOMERTYPEID")

    CustomertypeForm customertypeid;
    @Transient
    int selectoccupationid;
    @OneToOne
    @JoinColumn(name = "OCCUPATIONID", referencedColumnName = "OCCUPATIONID")

    OccupationForm occupationid;
    @Column(name = "DATEOFBIRTH")
    Date dateofbirth;
    @Transient
    int selectgenderid;
    @OneToOne
    @JoinColumn(name = "GENDERID", referencedColumnName = "GENDERID")

    GenderForm genderid;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "MOBILENO", length = 100)
    String mobileno;
    @Column(name = "LANDLINENO", length = 100)
    String landlineno;
    @Column(name = "ADDRESS", length = 4000)
    String address;
    @Transient
    int selectcityid;
    @OneToOne
    @JoinColumn(name = "CITYID", referencedColumnName = "CITYID")

    CityForm cityid;
    @Column(name = "PINCODE", length = 100)
    String pincode;
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

    public CustomerForm() {
    }

    public CustomerForm(CustomerForm obj) {
        this.customerid = obj.customerid;
        this.fullname = obj.fullname;
        this.customertypeid = obj.customertypeid;
        this.occupationid = obj.occupationid;
        this.dateofbirth = obj.dateofbirth;
        this.genderid = obj.genderid;
        this.email = obj.email;
        this.mobileno = obj.mobileno;
        this.landlineno = obj.landlineno;
        this.address = obj.address;
        this.contact1 = obj.contact1;
        this.contact2 = obj.contact2;
        this.contact3 = obj.contact3;
        this.cityid = obj.cityid;
        this.pincode = obj.pincode;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.userid = obj.userid;
        this.recordorder = obj.recordorder;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setCustomertypeid(CustomertypeForm customertypeid) {
        this.customertypeid = customertypeid;
    }

    public void setSelectcustomertypeid(int customertypeid) {
        this.selectcustomertypeid = customertypeid;
    }

    public void setOccupationid(OccupationForm occupationid) {
        this.occupationid = occupationid;
    }

    public void setSelectoccupationid(int occupationid) {
        this.selectoccupationid = occupationid;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setGenderid(GenderForm genderid) {
        this.genderid = genderid;
    }

    public void setSelectgenderid(int genderid) {
        this.selectgenderid = genderid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setLandlineno(String landlineno) {
        this.landlineno = landlineno;
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

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public int getCustomerid() {
        return customerid;
    }

    public String getFullname() {
        return fullname;
    }

    public CustomertypeForm getCustomertypeid() {
        return customertypeid;
    }

    public int getSelectcustomertypeid() {
        return selectcustomertypeid;
    }

    public OccupationForm getOccupationid() {
        return occupationid;
    }

    public int getSelectoccupationid() {
        return selectoccupationid;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public GenderForm getGenderid() {
        return genderid;
    }

    public int getSelectgenderid() {
        return selectgenderid;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getLandlineno() {
        return landlineno;
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

    public String getPincode() {
        return pincode;
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
}
