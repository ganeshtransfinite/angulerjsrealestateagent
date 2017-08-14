/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertytype;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Propertytype Vision
 */
public interface PropertytypeService {
    
     void addPropertytype(PropertytypeForm propertytypeForm);
    void updatePropertytype(PropertytypeForm propertytypeForm);
    void deletePropertytype(Integer id);
    TableForm getPropertytypeList(TableForm taleform);
 
    public PropertytypeForm editPropertytype(Integer id);
    
        public List<SelectCombo> getPropertytypeComboList(HttpServletRequest request);
}
