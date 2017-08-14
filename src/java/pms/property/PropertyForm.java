/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.property;

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
import pms.propertyfurnished.PropertyfurnishedForm;
import pms.city.CityForm;
import pms.propertytype.PropertytypeForm;
import pms.propertylbedrooms.PropertylbedroomsForm;
import admin.file.FileForm;
import pms.propertytobe.PropertytobeForm;
import pms.flatamenities.FlatamenitiesForm;
import pms.societyamenities.SocietyamenitiesForm;
import pms.propertypriority.PropertypriorityForm;
import pms.propertylocality.PropertylocalityForm;
import pms.propertyfacing.PropertyfacingForm;
import admin.user.UserForm;
import java.util.List;
import javax.persistence.CascadeType;
import pms.customer.CustomerForm;

/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Property")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "property")
public class PropertyForm implements Serializable {

    @Column(name = "PROPERTYID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int propertyid;
    @Transient
    int selectpropertytypeid;
    @OneToOne
    @JoinColumn(name = "PROPERTYTYPEID", referencedColumnName = "PROPERTYTYPEID")

    PropertytypeForm propertytypeid;
    @Transient
    int selectcustomerid;
    @OneToOne
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")

    CustomerForm customerid;
    @Column(name = "TOTALAREA", length = 100)
    String totalarea;
    @Column(name = "AMOUNT")
    float amount;

    @Transient
    private int[] selectpropertypriorityid;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_PROPERTY_PROPERTYPRIPROTY",
            joinColumns
            = @JoinColumn(name = "PROPERTYID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYPRIORITYID")
    )
    private Collection<PropertypriorityForm> propertypriorityid;

//    @Transient
//    int selectpropertypriorityid;
//    @OneToOne
//    @JoinColumn(name = "PROPERTYPRIORITYID", referencedColumnName = "PROPERTYPRIORITYID")
//
//    PropertypriorityForm propertypriorityid;
    @Column(name = "FLATNO", length = 4000)
    String flatno;
    @Column(name = "APT", length = 4000)
    String apt;
    @Column(name = "ADDRESS", length = 4000)
    String address;
    @Transient
    int selectcityid;
    @OneToOne
    @JoinColumn(name = "CITYID", referencedColumnName = "CITYID")

    CityForm cityid;
    @Transient
    int selectpropertylocalityid;
    @OneToOne
    @JoinColumn(name = "PROPERTYLOCALITYID", referencedColumnName = "PROPERTYLOCALITYID")

    PropertylocalityForm propertylocalityid;
    @Column(name = "LANDMARK", length = 4000)
    String landmark;
    @Column(name = "PROPERTYAGE", length = 100)
    String propertyage;
    @Column(name = "LEASEPERIOD", length = 100)
    String leaseperiod;
    @Transient
    int selectpropertylbedroomsid;
    @OneToOne
    @JoinColumn(name = "PROPERTYLBEDROOMSID", referencedColumnName = "PROPERTYLBEDROOMSID")

    PropertylbedroomsForm propertylbedroomsid;
    @Column(name = "FLOORNO", length = 100)
    String floorno;
    @Transient
    int selectpropertyfacingid;
    @OneToOne
    @JoinColumn(name = "PROPERTYFACINGID", referencedColumnName = "PROPERTYFACINGID")

    PropertyfacingForm propertyfacingid;
    @Transient
    int selectpropertyfurnishedid;
    @OneToOne
    @JoinColumn(name = "PROPERTYFURNISHEDID", referencedColumnName = "PROPERTYFURNISHEDID")

    PropertyfurnishedForm propertyfurnishedid;
    @Transient
    int selectimage1;
    @OneToOne

    @JoinColumn(name = "IMAGE1", referencedColumnName = "FILEID")

    FileForm image1;
    @Transient
    int selectimage2;
    @OneToOne

    @JoinColumn(name = "IMAGE2", referencedColumnName = "FILEID")

    FileForm image2;
    @Transient
    int selectimage3;
    @OneToOne

    @JoinColumn(name = "IMAGE3", referencedColumnName = "FILEID")

    FileForm image3;
    @Transient
    int selectimage4;
    @OneToOne
    @JoinColumn(name = "IMAGE4", referencedColumnName = "FILEID")

    FileForm image4;
    @Transient
    int selectimage5;
    @OneToOne
    @JoinColumn(name = "IMAGE5", referencedColumnName = "FILEID")

    FileForm image5;
    @Column(name = "REMARK", length = 4000)
    String remark;
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
    @Column(name = "DEPOSIT")
    float deposit;
    @Column(name = "SQFEETRATE", length = 4000)
    String sqfeetrate;
    @Transient
    int selectimage6;
    @OneToOne
    @JoinColumn(name = "IMAGE6", referencedColumnName = "FILEID")

    FileForm image6;
    @Transient
    int selectimage7;
    @OneToOne

    @JoinColumn(name = "IMAGE7", referencedColumnName = "FILEID")

    FileForm image7;
    @Transient
    int selectimage8;
    @OneToOne
    @JoinColumn(name = "IMAGE8", referencedColumnName = "FILEID")

    FileForm image8;
    @Transient
    int selectimage9;
    @OneToOne
    @JoinColumn(name = "IMAGE9", referencedColumnName = "FILEID")

    FileForm image9;
    @Transient
    int selectimage10;
    @OneToOne
    @JoinColumn(name = "IMAGE10", referencedColumnName = "FILEID")

    FileForm image10;
    @Column(name = "POSTEDON")
    Date postedon;
    @Transient
    int selectpropertytobeid;
    @OneToOne
    @JoinColumn(name = "PROPERTYTOBEID", referencedColumnName = "PROPERTYTOBEID")

    PropertytobeForm propertytobeid;
    @Column(name = "AVAILABLEFROM")
    Date availablefrom;
    @Transient
    int[] selectflatamenitiesid;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_PROPERTY_FLATAMENITIESID",
            joinColumns
            = @JoinColumn(name = "PROPERTYID"), inverseJoinColumns = @JoinColumn(name = "FLATAMENITIESID")
    )
    Collection<FlatamenitiesForm> flatamenitiesid;
    @Column(name = "STATUS")
    boolean status;
    @Transient
    int[] selectsocietyamenitiesid;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_PROPERTY_SOCIETYAMENITIESID",
            joinColumns
            = @JoinColumn(name = "PROPERTYID"), inverseJoinColumns = @JoinColumn(name = "SOCIETYAMENITIESID")
    )
    Collection<SocietyamenitiesForm> societyamenitiesid;
    @Column(name = "RECORDORDER")
    int recordorder;
    @Column(name = "BALCONY", length = 4000)
    String balcony;
    @Column(name = "BATHROOM", length = 4000)
    String bathroom;
    @Column(name = "TOTALFLOORNO", length = 4000)
    String totalfloorno;
    @Column(name = "PARKING")
    boolean parking;
    @Column(name = "KEYAVAILABILITY", length = 4000)
    String keyavailability;
    @Column(name = "KEYNAME", length = 4000)
    String keyname;
    @Column(name = "KEYMOBILE", length = 4000)
    String keymobile;
    @Column(name = "KEYADDRESS", length = 4000)
    String keyaddress;

    @Column(name = "CLOSEDATE")
    private Date closedate;

    @Column(name = "NEXTAVAILABLEDATE")
    private Date nextavilabledate;

    @Column(name = "CLOSEBY", length = 100)
    private String closeby;

    @Column(name = "PARKiNGTYPE", length = 4000)
    private String parkingtype;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @IndexColumn(name = "INDEX_COL")
//    @JoinTable(
//            name = "FK_PROPERTY_DATE",
//            joinColumns
//            = @JoinColumn(name = "PROPERTYID"), inverseJoinColumns = @JoinColumn(name = "PROPERTYDATEID")
//    )
//    private Collection<PropertyDateForm> propertydateid;
    @Column(name = "PREVID")
    private int previd;

    public PropertyForm() {
    }

    public PropertyForm(PropertyForm obj) {
        this.propertyid = obj.propertyid;
        this.propertytypeid = obj.propertytypeid;
        this.customerid = obj.customerid;
        this.totalarea = obj.totalarea;
        this.amount = obj.amount;
        this.propertypriorityid = obj.propertypriorityid;
        this.flatno = obj.flatno;
        this.apt = obj.apt;
        this.address = obj.address;
        this.cityid = obj.cityid;
        this.propertylocalityid = obj.propertylocalityid;
        this.landmark = obj.landmark;
        this.propertyage = obj.propertyage;
        this.leaseperiod = obj.leaseperiod;
        this.propertylbedroomsid = obj.propertylbedroomsid;
        this.floorno = obj.floorno;
        this.propertyfacingid = obj.propertyfacingid;
        this.propertyfurnishedid = obj.propertyfurnishedid;
        this.closedate = obj.closedate;
        this.closeby = obj.closeby;
//        this.image3 = obj.image3;
//        this.image4 = obj.image4;
//        this.image5 = obj.image5;
//        this.remark = obj.remark;
//        this.userid = obj.userid;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.deposit = obj.deposit;
        this.sqfeetrate = obj.sqfeetrate;
//        this.image6 = obj.image6;
//        this.image7 = obj.image7;
//        this.image8 = obj.image8;
//        this.image9 = obj.image9;
//        this.image10 = obj.image10;
        this.postedon = obj.postedon;
        this.propertytobeid = obj.propertytobeid;
        this.availablefrom = obj.availablefrom;
        this.flatamenitiesid = obj.flatamenitiesid;
        this.status = obj.status;
        this.societyamenitiesid = obj.societyamenitiesid;
        this.recordorder = obj.recordorder;
        this.balcony = obj.balcony;
        this.bathroom = obj.bathroom;
        this.totalfloorno = obj.totalfloorno;
        this.parking = obj.parking;
        this.keyavailability = obj.keyavailability;
        this.keyname = obj.keyname;
        this.keymobile = obj.keymobile;
        this.keyaddress = obj.keyaddress;
//        this.propertydateid = obj.propertydateid;
    }

    void setnewpropertyForm(PropertyForm obj) {
        this.propertyid = 0;
        this.previd = obj.getPropertyid();
        this.propertytypeid = obj.propertytypeid;
        this.customerid = obj.customerid;
        this.totalarea = obj.totalarea;
        this.amount = obj.amount;
        this.setPropertypriorityid(obj.getPropertypriorityid());
        this.flatno = obj.flatno;
        this.apt = obj.apt;
        this.address = obj.address;
        this.cityid = obj.cityid;
        this.propertylocalityid = obj.propertylocalityid;
        this.landmark = obj.landmark;
        this.propertyage = obj.propertyage;
        this.leaseperiod = obj.leaseperiod;
        this.propertylbedroomsid = obj.propertylbedroomsid;
        this.floorno = obj.floorno;
        this.propertyfacingid = obj.propertyfacingid;
        this.propertyfurnishedid = obj.propertyfurnishedid;
        this.closedate = obj.closedate;
        this.closeby = obj.closeby;
        this.image1 = obj.image1;
        this.image2 = obj.image2;
        this.image3 = obj.image3;
        this.image4 = obj.image4;
        this.image5 = obj.image5;
        this.remark = obj.remark;
        this.userid = obj.userid;
        this.active = obj.active;
        this.createddate = obj.createddate;
        this.modifyddate = obj.modifyddate;
        this.deposit = obj.deposit;
        this.sqfeetrate = obj.sqfeetrate;
        this.image6 = obj.image6;
        this.image7 = obj.image7;
        this.image8 = obj.image8;
        this.image9 = obj.image9;
        this.image10 = obj.image10;
        this.postedon = obj.postedon;
        this.propertytobeid = obj.propertytobeid;
        this.availablefrom = obj.availablefrom;

        this.flatamenitiesid = new ArrayList();
        for (int i = 0; obj.flatamenitiesid != null && i < obj.flatamenitiesid.size(); i++) {
            this.flatamenitiesid.add(((List<FlatamenitiesForm>) obj.flatamenitiesid).get(i));
        }

        this.societyamenitiesid = new ArrayList();
        for (int i = 0; obj.societyamenitiesid != null && i < obj.societyamenitiesid.size(); i++) {
            this.societyamenitiesid.add(((List<SocietyamenitiesForm>) obj.societyamenitiesid).get(i));
        }
        this.status = obj.status;

        this.recordorder = obj.recordorder;
        this.balcony = obj.balcony;
        this.bathroom = obj.bathroom;
        this.totalfloorno = obj.totalfloorno;
        this.parking = obj.parking;
        this.keyavailability = obj.keyavailability;
        this.keyname = obj.keyname;
        this.keymobile = obj.keymobile;
        this.keyaddress = obj.keyaddress;
        this.nextavilabledate = obj.nextavilabledate;
    }

    public void setPropertyid(int propertyid) {
        this.propertyid = propertyid;
    }

    public void setPropertytypeid(PropertytypeForm propertytypeid) {
        this.propertytypeid = propertytypeid;
    }

    public void setSelectpropertytypeid(int propertytypeid) {
        this.selectpropertytypeid = propertytypeid;
    }

    public void setCustomerid(CustomerForm customerid) {
        this.customerid = customerid;
    }

    public void setSelectcustomerid(int customerid) {
        this.selectcustomerid = customerid;
    }

    public void setTotalarea(String totalarea) {
        this.totalarea = totalarea;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

   

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }

    public void setApt(String apt) {
        this.apt = apt;
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

    public void setPropertylocalityid(PropertylocalityForm propertylocalityid) {
        this.propertylocalityid = propertylocalityid;
    }

    public void setSelectpropertylocalityid(int propertylocalityid) {
        this.selectpropertylocalityid = propertylocalityid;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setPropertyage(String propertyage) {
        this.propertyage = propertyage;
    }

    public void setLeaseperiod(String leaseperiod) {
        this.leaseperiod = leaseperiod;
    }

    public void setPropertylbedroomsid(PropertylbedroomsForm propertylbedroomsid) {
        this.propertylbedroomsid = propertylbedroomsid;
    }

    public void setSelectpropertylbedroomsid(int propertylbedroomsid) {
        this.selectpropertylbedroomsid = propertylbedroomsid;
    }

    public void setFloorno(String floorno) {
        this.floorno = floorno;
    }

    public void setPropertyfacingid(PropertyfacingForm propertyfacingid) {
        this.propertyfacingid = propertyfacingid;
    }

    public void setSelectpropertyfacingid(int propertyfacingid) {
        this.selectpropertyfacingid = propertyfacingid;
    }

    public void setPropertyfurnishedid(PropertyfurnishedForm propertyfurnishedid) {
        this.propertyfurnishedid = propertyfurnishedid;
    }

    public void setSelectpropertyfurnishedid(int propertyfurnishedid) {
        this.selectpropertyfurnishedid = propertyfurnishedid;
    }

    public void setImage1(FileForm image1) {
        this.image1 = image1;
    }

    public void setSelectimage1(int image1) {
        this.selectimage1 = image1;
    }

    public void setImage2(FileForm image2) {
        this.image2 = image2;
    }

    public void setSelectimage2(int image2) {
        this.selectimage2 = image2;
    }

    public void setImage3(FileForm image3) {
        this.image3 = image3;
    }

    public void setSelectimage3(int image3) {
        this.selectimage3 = image3;
    }

    public void setImage4(FileForm image4) {
        this.image4 = image4;
    }

    public void setSelectimage4(int image4) {
        this.selectimage4 = image4;
    }

    public void setImage5(FileForm image5) {
        this.image5 = image5;
    }

    public void setSelectimage5(int image5) {
        this.selectimage5 = image5;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public void setSqfeetrate(String sqfeetrate) {
        this.sqfeetrate = sqfeetrate;
    }

    public void setImage6(FileForm image6) {
        this.image6 = image6;
    }

    public void setSelectimage6(int image6) {
        this.selectimage6 = image6;
    }

    public void setImage7(FileForm image7) {
        this.image7 = image7;
    }

    public void setSelectimage7(int image7) {
        this.selectimage7 = image7;
    }

    public void setImage8(FileForm image8) {
        this.image8 = image8;
    }

    public void setSelectimage8(int image8) {
        this.selectimage8 = image8;
    }

    public void setImage9(FileForm image9) {
        this.image9 = image9;
    }

    public void setSelectimage9(int image9) {
        this.selectimage9 = image9;
    }

    public void setImage10(FileForm image10) {
        this.image10 = image10;
    }

    public void setSelectimage10(int image10) {
        this.selectimage10 = image10;
    }

    public void setPostedon(Date postedon) {
        this.postedon = postedon;
    }

    public void setPropertytobeid(PropertytobeForm propertytobeid) {
        this.propertytobeid = propertytobeid;
    }

    public void setSelectpropertytobeid(int propertytobeid) {
        this.selectpropertytobeid = propertytobeid;
    }

    public void setAvailablefrom(Date availablefrom) {
        this.availablefrom = availablefrom;
    }

    public void setFlatamenitiesid(Collection<FlatamenitiesForm> flatamenitiesid) {
        this.flatamenitiesid = flatamenitiesid;
    }

    public void setSelectflatamenitiesid(int[] flatamenitiesid) {
        this.selectflatamenitiesid = flatamenitiesid;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setSocietyamenitiesid(Collection<SocietyamenitiesForm> societyamenitiesid) {
        this.societyamenitiesid = societyamenitiesid;
    }

    public void setSelectsocietyamenitiesid(int[] societyamenitiesid) {
        this.selectsocietyamenitiesid = societyamenitiesid;
    }

    public void setRecordorder(int recordorder) {
        this.recordorder = recordorder;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public void setTotalfloorno(String totalfloorno) {
        this.totalfloorno = totalfloorno;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public void setKeyavailability(String keyavailability) {
        this.keyavailability = keyavailability;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public void setKeymobile(String keymobile) {
        this.keymobile = keymobile;
    }

    public void setKeyaddress(String keyaddress) {
        this.keyaddress = keyaddress;
    }

    public int getPropertyid() {
        return propertyid;
    }

    public PropertytypeForm getPropertytypeid() {
        return propertytypeid;
    }

    public int getSelectpropertytypeid() {
        return selectpropertytypeid;
    }

    public CustomerForm getCustomerid() {
        return customerid;
    }

    public int getSelectcustomerid() {
        return selectcustomerid;
    }

    public String getTotalarea() {
        return totalarea;
    }

    public float getAmount() {
        return amount;
    }

   

    public String getFlatno() {
        return flatno;
    }

    public String getApt() {
        return apt;
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

    public PropertylocalityForm getPropertylocalityid() {
        return propertylocalityid;
    }

    public int getSelectpropertylocalityid() {
        return selectpropertylocalityid;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getPropertyage() {
        return propertyage;
    }

    public String getLeaseperiod() {
        return leaseperiod;
    }

    public PropertylbedroomsForm getPropertylbedroomsid() {
        return propertylbedroomsid;
    }

    public int getSelectpropertylbedroomsid() {
        return selectpropertylbedroomsid;
    }

    public String getFloorno() {
        return floorno;
    }

    public PropertyfacingForm getPropertyfacingid() {
        return propertyfacingid;
    }

    public int getSelectpropertyfacingid() {
        return selectpropertyfacingid;
    }

    public PropertyfurnishedForm getPropertyfurnishedid() {
        return propertyfurnishedid;
    }

    public int getSelectpropertyfurnishedid() {
        return selectpropertyfurnishedid;
    }

    public FileForm getImage1() {
        return image1;
    }

    public int getSelectimage1() {
        return selectimage1;
    }

    public FileForm getImage2() {
        return image2;
    }

    public int getSelectimage2() {
        return selectimage2;
    }

    public FileForm getImage3() {
        return image3;
    }

    public int getSelectimage3() {
        return selectimage3;
    }

    public FileForm getImage4() {
        return image4;
    }

    public int getSelectimage4() {
        return selectimage4;
    }

    public FileForm getImage5() {
        return image5;
    }

    public int getSelectimage5() {
        return selectimage5;
    }

    public String getRemark() {
        return remark;
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

    public float getDeposit() {
        return deposit;
    }

    public String getSqfeetrate() {
        return sqfeetrate;
    }

    public FileForm getImage6() {
        return image6;
    }

    public int getSelectimage6() {
        return selectimage6;
    }

    public FileForm getImage7() {
        return image7;
    }

    public int getSelectimage7() {
        return selectimage7;
    }

    public FileForm getImage8() {
        return image8;
    }

    public int getSelectimage8() {
        return selectimage8;
    }

    public FileForm getImage9() {
        return image9;
    }

    public int getSelectimage9() {
        return selectimage9;
    }

    public FileForm getImage10() {
        return image10;
    }

    public int getSelectimage10() {
        return selectimage10;
    }

    public Date getPostedon() {
        return postedon;
    }

    public PropertytobeForm getPropertytobeid() {
        return propertytobeid;
    }

    public int getSelectpropertytobeid() {
        return selectpropertytobeid;
    }

    public Date getAvailablefrom() {
        return availablefrom;
    }

    public Collection<FlatamenitiesForm> getFlatamenitiesid() {
        return flatamenitiesid;
    }

    public int[] getSelectflatamenitiesid() {
        return selectflatamenitiesid;
    }

    public boolean getStatus() {
        return status;
    }

    public Collection<SocietyamenitiesForm> getSocietyamenitiesid() {
        return societyamenitiesid;
    }

    public int[] getSelectsocietyamenitiesid() {
        return selectsocietyamenitiesid;
    }

    public int getRecordorder() {
        return recordorder;
    }

    public String getBalcony() {
        return balcony;
    }

    public String getBathroom() {
        return bathroom;
    }

    public String getTotalfloorno() {
        return totalfloorno;
    }

    public boolean getParking() {
        return parking;
    }

    public String getKeyavailability() {
        return keyavailability;
    }

    public String getKeyname() {
        return keyname;
    }

    public String getKeymobile() {
        return keymobile;
    }

    public String getKeyaddress() {
        return keyaddress;
    }

    /**
     * @return the propertydateid
     */
//    public Collection<PropertyDateForm> getPropertydateid() {
//        return propertydateid;
//    }
//
//    /**
//     * @param propertydateid the propertydateid to set
//     */
//    public void setPropertydateid(Collection<PropertyDateForm> propertydateid) {
//        this.propertydateid = propertydateid;
//    }
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
     * @return the closedate
     */
    public Date getClosedate() {
        return closedate;
    }

    /**
     * @param closedate the closedate to set
     */
    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    /**
     * @return the nextavilabledate
     */
    public Date getNextavilabledate() {
        return nextavilabledate;
    }

    /**
     * @param nextavilabledate the nextavilabledate to set
     */
    public void setNextavilabledate(Date nextavilabledate) {
        this.nextavilabledate = nextavilabledate;
    }

    /**
     * @return the previd
     */
    public int getPrevid() {
        return previd;
    }

    /**
     * @param previd the previd to set
     */
    public void setPrevid(int previd) {
        this.previd = previd;
    }

    /**
     * @return the selectpropertypriorityid
     */
    public int[] getSelectpropertypriorityid() {
        return selectpropertypriorityid;
    }

    /**
     * @param selectpropertypriorityid the selectpropertypriorityid to set
     */
    public void setSelectpropertypriorityid(int[] selectpropertypriorityid) {
        this.selectpropertypriorityid = selectpropertypriorityid;
    }

    /**
     * @return the propertypriorityid
     */
    public Collection<PropertypriorityForm> getPropertypriorityid() {
        return propertypriorityid;
    }

    /**
     * @param propertypriorityid the propertypriorityid to set
     */
    public void setPropertypriorityid(Collection<PropertypriorityForm> propertypriorityid) {
        this.propertypriorityid = propertypriorityid;
    }

    /**
     * @return the parkingtype
     */
    public String getParkingtype() {
        return parkingtype;
    }

    /**
     * @param parkingtype the parkingtype to set
     */
    public void setParkingtype(String parkingtype) {
        this.parkingtype = parkingtype;
    }
}
