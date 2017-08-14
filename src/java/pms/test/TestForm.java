/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.test;
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
import pms.flatamenities.FlatamenitiesForm;
import pms.societyamenities.SocietyamenitiesForm;
import admin.user.UserForm;
/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Test")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "test")
public class  TestForm implements Serializable {
     @Column(name = "TESTID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
int testid;
@Column(name = "NAME" , length=2000) 
String name;
@Transient
int[] selectflatamenitiesid;
@ManyToMany
  @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_TEST_FLATAMENITIESID",
            joinColumns
            = @JoinColumn(name = "TESTID" ),  inverseJoinColumns = @JoinColumn(name = "FLATAMENITIESID") 
    )  
Collection<FlatamenitiesForm> flatamenitiesid;
@Transient
int[] selectsocietyamenitiesid;
@ManyToMany
  @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name = "INDEX_COL")
    @JoinTable(
            name = "FK_TEST_SOCIETYAMENITIESID",
            joinColumns
            = @JoinColumn(name = "TESTID" ),  inverseJoinColumns = @JoinColumn(name = "SOCIETYAMENITIESID") 
    )  
Collection<SocietyamenitiesForm> societyamenitiesid;
@Column(name = "ACTIVE" , length=1) 
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
         public TestForm() {
    }
    public TestForm(TestForm obj) {
          this.testid=obj.testid;
this.name=obj.name;
this.flatamenitiesid=obj.flatamenitiesid;
this.societyamenitiesid=obj.societyamenitiesid;
this.active=obj.active;
this.createddate=obj.createddate;
this.modifyddate=obj.modifyddate;
this.userid=obj.userid;
this.recordorder=obj.recordorder;
    } 
   public void setTestid(int testid) { this.testid = testid;  }
public void setName(String name) { this.name = name;  }
public void setFlatamenitiesid(Collection<FlatamenitiesForm> flatamenitiesid) { this.flatamenitiesid = flatamenitiesid;  }
public void setSelectflatamenitiesid(int[] flatamenitiesid) { this.selectflatamenitiesid = flatamenitiesid;  }
public void setSocietyamenitiesid(Collection<SocietyamenitiesForm> societyamenitiesid) { this.societyamenitiesid = societyamenitiesid;  }
public void setSelectsocietyamenitiesid(int[] societyamenitiesid) { this.selectsocietyamenitiesid = societyamenitiesid;  }
public void setActive(String active) { this.active = active;  }
public void setCreateddate(Date createddate) { this.createddate = createddate;  }
public void setModifyddate(Date modifyddate) { this.modifyddate = modifyddate;  }
public void setUserid(UserForm userid) { this.userid = userid;  }
public void setSelectuserid(int userid) { this.selectuserid = userid;  }
public void setRecordorder(int recordorder) { this.recordorder = recordorder;  }
   public int getTestid() { return testid; }
public String getName() { return name; }
public Collection<FlatamenitiesForm> getFlatamenitiesid() { return flatamenitiesid; }
public int[] getSelectflatamenitiesid() { return selectflatamenitiesid; }
public Collection<SocietyamenitiesForm> getSocietyamenitiesid() { return societyamenitiesid; }
public int[] getSelectsocietyamenitiesid() { return selectsocietyamenitiesid; }
public String getActive() { return active; }
public Date getCreateddate() { return createddate; }
public Date getModifyddate() { return modifyddate; }
public UserForm getUserid() { return userid; }
public int getSelectuserid() { return selectuserid; }
public int getRecordorder() { return recordorder; }
}
