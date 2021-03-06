/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.budgetrent;
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
@Entity(name = "Budgetrent")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "budgetrent")
public class  BudgetrentForm implements Serializable {
     @Column(name = "BUDGETRENTID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
int budgetrentid;
@Column(name = "NAME" , length=1000) 
String name;
@Column(name = "AMOUNT") 
float amount;
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
         public BudgetrentForm() {
    }
    public BudgetrentForm(BudgetrentForm obj) {
          this.budgetrentid=obj.budgetrentid;
this.name=obj.name;
this.amount=obj.amount;
this.active=obj.active;
this.createddate=obj.createddate;
this.modifyddate=obj.modifyddate;
this.userid=obj.userid;
this.recordorder=obj.recordorder;
    } 
   public void setBudgetrentid(int budgetrentid) { this.budgetrentid = budgetrentid;  }
public void setName(String name) { this.name = name;  }
public void setAmount(float amount) { this.amount = amount;  }
public void setActive(String active) { this.active = active;  }
public void setCreateddate(Date createddate) { this.createddate = createddate;  }
public void setModifyddate(Date modifyddate) { this.modifyddate = modifyddate;  }
public void setUserid(UserForm userid) { this.userid = userid;  }
public void setSelectuserid(int userid) { this.selectuserid = userid;  }
public void setRecordorder(int recordorder) { this.recordorder = recordorder;  }
   public int getBudgetrentid() { return budgetrentid; }
public String getName() { return name; }
public float getAmount() { return amount; }
public String getActive() { return active; }
public Date getCreateddate() { return createddate; }
public Date getModifyddate() { return modifyddate; }
public UserForm getUserid() { return userid; }
public int getSelectuserid() { return selectuserid; }
public int getRecordorder() { return recordorder; }
}
