/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertypriority;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Propertypriority Vision
 */
public interface PropertypriorityService {
    
     void addPropertypriority(PropertypriorityForm propertypriorityForm);
    void updatePropertypriority(PropertypriorityForm propertypriorityForm);
    void deletePropertypriority(Integer id);
    TableForm getPropertypriorityList(TableForm taleform);
 
    public PropertypriorityForm editPropertypriority(Integer id);
    
        public List<SelectCombo> getPropertypriorityComboList(HttpServletRequest request);
}
