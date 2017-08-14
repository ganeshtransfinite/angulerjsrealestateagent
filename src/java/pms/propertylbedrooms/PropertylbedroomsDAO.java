/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertylbedrooms;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertylbedroomsDAO {
 
    void addPropertylbedrooms(PropertylbedroomsForm propertylbedroomsForm);
    public PropertylbedroomsForm editPropertylbedrooms(Integer id);
    void updatePropertylbedrooms(PropertylbedroomsForm propertylbedroomsForm);
    void deletePropertylbedrooms(Integer id);
    TableForm getPropertylbedroomsList(TableForm tableFrom);
 
       public List<SelectCombo> getPropertylbedroomsComboList(HttpServletRequest request);
}
