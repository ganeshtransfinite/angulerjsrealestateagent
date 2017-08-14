/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertypriority;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertypriorityDAO {
 
    void addPropertypriority(PropertypriorityForm propertypriorityForm);
    public PropertypriorityForm editPropertypriority(Integer id);
    void updatePropertypriority(PropertypriorityForm propertypriorityForm);
    void deletePropertypriority(Integer id);
    TableForm getPropertypriorityList(TableForm tableFrom);
 
       public List<SelectCombo> getPropertypriorityComboList(HttpServletRequest request);
}
