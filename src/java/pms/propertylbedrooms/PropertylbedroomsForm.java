/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertylbedrooms;
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
import admin.user.UserForm;
/**
 *
 * @author user
 */
/**
 *
 * @author Vision
 */
@Entity(name = "Propertylbedrooms")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "propertylbedrooms")
public class  PropertylbedroomsForm implements Serializable {
     @Column(name = "PROPERTYLBEDROOMSID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
int propertylbedroomsid;
@Column(name = "NAME" , length=4000) 
String name;
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
         public PropertylbedroomsForm() {
    }
    public PropertylbedroomsForm(PropertylbedroomsForm obj) {
          this.propertylbedroomsid=obj.propertylbedroomsid;
this.name=obj.name;
this.active=obj.active;
this.createddate=obj.createddate;
this.modifyddate=obj.modifyddate;
this.userid=obj.userid;
this.recordorder=obj.recordorder;
    } 
   public void setPropertylbedroomsid(int propertylbedroomsid) { this.propertylbedroomsid = propertylbedroomsid;  }
public void setName(String name) { this.name = name;  }
public void setActive(String active) { this.active = active;  }
public void setCreateddate(Date createddate) { this.createddate = createddate;  }
public void setModifyddate(Date modifyddate) { this.modifyddate = modifyddate;  }
public void setUserid(UserForm userid) { this.userid = userid;  }
public void setSelectuserid(int userid) { this.selectuserid = userid;  }
public void setRecordorder(int recordorder) { this.recordorder = recordorder;  }
   public int getPropertylbedroomsid() { return propertylbedroomsid; }
public String getName() { return name; }
public String getActive() { return active; }
public Date getCreateddate() { return createddate; }
public Date getModifyddate() { return modifyddate; }
public UserForm getUserid() { return userid; }
public int getSelectuserid() { return selectuserid; }
public int getRecordorder() { return recordorder; }
}
