/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertylbedrooms;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Propertylbedrooms Vision
 */
public interface PropertylbedroomsService {
    
     void addPropertylbedrooms(PropertylbedroomsForm propertylbedroomsForm);
    void updatePropertylbedrooms(PropertylbedroomsForm propertylbedroomsForm);
    void deletePropertylbedrooms(Integer id);
    TableForm getPropertylbedroomsList(TableForm taleform);
 
    public PropertylbedroomsForm editPropertylbedrooms(Integer id);
    
        public List<SelectCombo> getPropertylbedroomsComboList(HttpServletRequest request);
}
