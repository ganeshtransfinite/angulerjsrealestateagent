/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity(name = "PropertyDate")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "propertydate")
public class PropertyDateForm implements Serializable {

    @Column(name = "PROPERTYDATEID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int propertydateid;
 
    @Column(name = "POSTEDON")
    private Date postedon;

    @Column(name = "CLOSEDATE")
    private Date closedate;

    @Column(name = "AVILABLEDATE")
    private Date avilabledate;

    @Column(name = "CLOSEBY", length = 100)
    private String closeby;
   

    /**
     * @return the propertydateid
     */
    public int getPropertydateid() {
        return propertydateid;
    }

    /**
     * @param propertydateid the propertydateid to set
     */
    public void setPropertydateid(int propertydateid) {
        this.propertydateid = propertydateid;
    }

    /**
     * @return the postedon
     */
    public Date getPostedon() {
        return postedon;
    }

    /**
     * @param postedon the postedon to set
     */
    public void setPostedon(Date postedon) {
        this.postedon = postedon;
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
     * @return the avilabledate
     */
    public Date getAvilabledate() {
        return avilabledate;
    }

    /**
     * @param avilabledate the avilabledate to set
     */
    public void setAvilabledate(Date avilabledate) {
        this.avilabledate = avilabledate;
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

    

}
