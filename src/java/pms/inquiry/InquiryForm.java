/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.inquiry;

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
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.ManyToMany;
import pms.property.PropertyForm;
import pms.city.CityForm;
import pms.propertylbedrooms.PropertylbedroomsForm;
import pms.propertypriority.PropertypriorityForm;
import pms.propertylocality.PropertylocalityForm;
import pms.propertyfacing.PropertyfacingForm;
import admin.user.UserForm;
import pms.flatamenities.FlatamenitiesForm;
import pms.propertytobe.PropertytobeForm;
import pms.propertytype.PropertytypeForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Inquiry")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "inquiry")
public class InquiryForm implements Serializable {

    @Column(name = "INQUIRYID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int inquiryid;
    @Column(name = "DATEOFINQUIRY")
    Date dateofinquiry;
    @Column(name = "NAME", length = 4000)
    String name;
    @Column(name = "MOBILENO", length = 100)
    String mobileno;
    @Column(name = "EMAIL", length = 1000)
    String email;
    @Column(name = "BUDGET")
    float budget;
    @Column(name = "FROMPERIOD")
    Date fromperiod;

    @Transient
    int[] selectpropertypriorityid;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_INQUIRY_PROPERTYPRIORITYID",
            joinColumns
            = @JoinColumn(name = "INQUIRYID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYPRIORITYID")
    )
    Collection<PropertypriorityForm> propertypriorityid;

    @Transient
    int[] selectpropertylbedroomsid;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_INQUIRY_PROPERTYLBEDROOMSID",
            joinColumns
            = @JoinColumn(name = "INQUIRYID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYLBEDROOMSID")
    )
    Collection<PropertylbedroomsForm> propertylbedroomsid;

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
            name = "FK_INQUIRY_PROPERTYLOCALITYID",
            joinColumns
            = @JoinColumn(name = "INQUIRYID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYLOCALITYID")
    )
    Collection<PropertylocalityForm> propertylocalityid;

    @Transient
    int selectpropertyfacingid;
    @OneToOne
    @JoinColumn(name = "PROPERTYFACINGID", referencedColumnName = "PROPERTYFACINGID")

    PropertyfacingForm propertyfacingid;
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

    @Column(name = "REMARKS", length = 4000)
    private String remark;

    @Column(name = "NEXTDATE")
    private Date nextdate;

    @Transient
    private int[] selectpropertytobeid;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_INQUIRY_PROPERTYTOBEID",
            joinColumns
            = @JoinColumn(name = "INQUIRYID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYTOBEID")
    )
    Collection<PropertytobeForm> propertytobeid;

    @Column(name = "CONTACT1", length = 100)
    private String contact1;
    @Column(name = "CONTACT2", length = 100)
    private String contact2;
    @Column(name = "CONTACT3", length = 100)
    private String contact3;

    @Column(name = "EXECUTIV", length = 1000)
    private String excutive;

    @Column(name = "CLOSEBY", length = 1000)
    private String closeby;

     @Column(name = "CLOSEFLAG" )
    private boolean closeflag;

    @Transient
    private int[] selectpropertytypeid;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL") 
    @JoinTable(
            name = "FK_INQUIRY_PROPERTYTYPEID",
            joinColumns
            = @JoinColumn(name = "INQUIRYID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYTYPEID")
    )
    private Collection<PropertytypeForm> propertytypeid;

    public InquiryForm() {
    }

    public InquiryForm(InquiryForm obj) {
        this.inquiryid = obj.inquiryid;
        this.dateofinquiry = obj.dateofinquiry;
        this.name = obj.name;
        this.mobileno = obj.mobileno;
        this.email = obj.email;
        this.budget = obj.budget;
        this.fromperiod = obj.fromperiod;
        this.propertypriorityid = obj.propertypriorityid;
        this.propertylbedroomsid = obj.propertylbedroomsid;
        this.cityid = obj.cityid;
        this.propertylocalityid = obj.propertylocalityid;
        this.propertyfacingid = obj.propertyfacingid;
        this.userid = obj.userid;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.recordorder = obj.recordorder;
        this.contact1 = obj.contact1;
        this.contact2 = obj.contact2;
        this.contact3 = obj.contact3;
        this.remark = obj.remark;
        this.nextdate = obj.nextdate;
    }

    public void setInquiryid(int inquiryid) {
        this.inquiryid = inquiryid;
    }

    public void setDateofinquiry(Date dateofinquiry) {
        this.dateofinquiry = dateofinquiry;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void setFromperiod(Date fromperiod) {
        this.fromperiod = fromperiod;
    }

    public void setPropertypriorityid(Collection<PropertypriorityForm> propertypriorityid) {
        this.propertypriorityid = propertypriorityid;
    }

    public void setSelectpropertypriorityid(int[] propertypriorityid) {
        this.selectpropertypriorityid = propertypriorityid;
    }

    public void setPropertylbedroomsid(Collection<PropertylbedroomsForm> propertylbedroomsid) {
        this.propertylbedroomsid = propertylbedroomsid;
    }

    public void setSelectpropertylbedroomsid(int[] propertylbedroomsid) {
        this.selectpropertylbedroomsid = propertylbedroomsid;
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

    public void setPropertyfacingid(PropertyfacingForm propertyfacingid) {
        this.propertyfacingid = propertyfacingid;
    }

    public void setSelectpropertyfacingid(int propertyfacingid) {
        this.selectpropertyfacingid = propertyfacingid;
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

    public int getInquiryid() {
        return inquiryid;
    }

    public Date getDateofinquiry() {
        return dateofinquiry;
    }

    public String getName() {
        return name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getEmail() {
        return email;
    }

    public float getBudget() {
        return budget;
    }

    public Date getFromperiod() {
        return fromperiod;
    }

    public Collection<PropertypriorityForm> getPropertypriorityid() {
        return propertypriorityid;
    }

    public int[] getSelectpropertypriorityid() {
        return selectpropertypriorityid;
    }

    public Collection<PropertylbedroomsForm> getPropertylbedroomsid() {
        return propertylbedroomsid;
    }

    public int[] getSelectpropertylbedroomsid() {
        return selectpropertylbedroomsid;
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

    public PropertyfacingForm getPropertyfacingid() {
        return propertyfacingid;
    }

    public int getSelectpropertyfacingid() {
        return selectpropertyfacingid;
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

    /**
     * @return the propertytobeid
     */
    public Collection<PropertytobeForm> getPropertytobeid() {
        return propertytobeid;
    }

    /**
     * @param propertytobeid the propertytobeid to set
     */
    public void setPropertytobeid(Collection<PropertytobeForm> propertytobeid) {
        this.propertytobeid = propertytobeid;
    }

    /**
     * @return the selectpropertytobeid
     */
    public int[] getSelectpropertytobeid() {
        return selectpropertytobeid;
    }

    /**
     * @param selectpropertytobeid the selectpropertytobeid to set
     */
    public void setSelectpropertytobeid(int[] selectpropertytobeid) {
        this.selectpropertytobeid = selectpropertytobeid;
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
     * @return the nextdate
     */
    public Date getNextdate() {
        return nextdate;
    }

    /**
     * @param nextdate the nextdate to set
     */
    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }

    /**
     * @return the selectpropertytypeid
     */
    public int[] getSelectpropertytypeid() {
        return selectpropertytypeid;
    }

    /**
     * @param selectpropertytypeid the selectpropertytypeid to set
     */
    public void setSelectpropertytypeid(int[] selectpropertytypeid) {
        this.selectpropertytypeid = selectpropertytypeid;
    }

    /**
     * @return the propertytypeid
     */
    public Collection<PropertytypeForm> getPropertytypeid() {
        return propertytypeid;
    }

    /**
     * @param propertytypeid the propertytypeid to set
     */
    public void setPropertytypeid(Collection<PropertytypeForm> propertytypeid) {
        this.propertytypeid = propertytypeid;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the excutive
     */
    public String getExcutive() {
        return excutive;
    }

    /**
     * @param excutive the excutive to set
     */
    public void setExcutive(String excutive) {
        this.excutive = excutive;
    }

    /**
     * @return the closeby
     */
    public String getCloseby() {
        return closeby;
    }

    /**
     * @param closeby the closeby to set
     */
    public void setCloseby(String closeby) {
        this.closeby = closeby;
    }

    /**
     * @return the closeflag
     */
    public boolean isCloseflag() {
        return closeflag;
    }

    /**
     * @param closeflag the closeflag to set
     */
    public void setCloseflag(boolean closeflag) {
        this.closeflag = closeflag;
    }
}
