/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertyfurnished;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Propertyfurnished Vision
 */
public interface PropertyfurnishedService {
    
     void addPropertyfurnished(PropertyfurnishedForm propertyfurnishedForm);
    void updatePropertyfurnished(PropertyfurnishedForm propertyfurnishedForm);
    void deletePropertyfurnished(Integer id);
    TableForm getPropertyfurnishedList(TableForm taleform);
 
    public PropertyfurnishedForm editPropertyfurnished(Integer id);
    
        public List<SelectCombo> getPropertyfurnishedComboList(HttpServletRequest request);
}
