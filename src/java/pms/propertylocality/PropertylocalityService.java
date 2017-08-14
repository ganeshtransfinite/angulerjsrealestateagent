/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertylocality;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Propertylocality Vision
 */
public interface PropertylocalityService {
    
     void addPropertylocality(PropertylocalityForm propertylocalityForm);
    void updatePropertylocality(PropertylocalityForm propertylocalityForm);
    void deletePropertylocality(Integer id);
    TableForm getPropertylocalityList(TableForm taleform);
 
    public PropertylocalityForm editPropertylocality(Integer id);
    
        public List<SelectCombo> getPropertylocalityComboList(HttpServletRequest request);
}
