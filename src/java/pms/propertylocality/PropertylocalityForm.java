/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertylocality;
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
import pms.city.CityForm;
import admin.user.UserForm;
/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Propertylocality")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "propertylocality")
public class  PropertylocalityForm implements Serializable {
     @Column(name = "PROPERTYLOCALITYID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
int propertylocalityid;
@Column(name = "NAME" , length=1000) 
String name;
@Transient
int selectcityid;
@OneToOne
@JoinColumn(name = "CITYID", referencedColumnName = "CITYID")
 
CityForm cityid;
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
         public PropertylocalityForm() {
    }
    public PropertylocalityForm(PropertylocalityForm obj) {
          this.propertylocalityid=obj.propertylocalityid;
this.name=obj.name;
this.cityid=obj.cityid;
this.active=obj.active;
this.createddate=obj.createddate;
this.modifyddate=obj.modifyddate;
this.userid=obj.userid;
this.recordorder=obj.recordorder;
    } 
   public void setPropertylocalityid(int propertylocalityid) { this.propertylocalityid = propertylocalityid;  }
public void setName(String name) { this.name = name;  }
public void setCityid(CityForm cityid) { this.cityid = cityid;  }
public void setSelectcityid(int cityid) { this.selectcityid = cityid;  }
public void setActive(String active) { this.active = active;  }
public void setCreateddate(Date createddate) { this.createddate = createddate;  }
public void setModifyddate(Date modifyddate) { this.modifyddate = modifyddate;  }
public void setUserid(UserForm userid) { this.userid = userid;  }
public void setSelectuserid(int userid) { this.selectuserid = userid;  }
public void setRecordorder(int recordorder) { this.recordorder = recordorder;  }
   public int getPropertylocalityid() { return propertylocalityid; }
public String getName() { return name; }
public CityForm getCityid() { return cityid; }
public int getSelectcityid() { return selectcityid; }
public String getActive() { return active; }
public Date getCreateddate() { return createddate; }
public Date getModifyddate() { return modifyddate; }
public UserForm getUserid() { return userid; }
public int getSelectuserid() { return selectuserid; }
public int getRecordorder() { return recordorder; }
}
