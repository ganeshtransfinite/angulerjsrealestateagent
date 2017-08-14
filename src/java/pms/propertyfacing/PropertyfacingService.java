/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertyfacing;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Propertyfacing Vision
 */
public interface PropertyfacingService {
    
     void addPropertyfacing(PropertyfacingForm propertyfacingForm);
    void updatePropertyfacing(PropertyfacingForm propertyfacingForm);
    void deletePropertyfacing(Integer id);
    TableForm getPropertyfacingList(TableForm taleform);
 
    public PropertyfacingForm editPropertyfacing(Integer id);
    
        public List<SelectCombo> getPropertyfacingComboList(HttpServletRequest request);
}
