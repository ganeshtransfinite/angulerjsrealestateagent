/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertytobe;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Propertytobe Vision
 */
public interface PropertytobeService {
    
     void addPropertytobe(PropertytobeForm propertytobeForm);
    void updatePropertytobe(PropertytobeForm propertytobeForm);
    void deletePropertytobe(Integer id);
    TableForm getPropertytobeList(TableForm taleform);
 
    public PropertytobeForm editPropertytobe(Integer id);
    
        public List<SelectCombo> getPropertytobeComboList(HttpServletRequest request);
}
