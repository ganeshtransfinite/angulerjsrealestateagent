/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertylocality;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertylocalityDAO {
 
    void addPropertylocality(PropertylocalityForm propertylocalityForm);
    public PropertylocalityForm editPropertylocality(Integer id);
    void updatePropertylocality(PropertylocalityForm propertylocalityForm);
    void deletePropertylocality(Integer id);
    TableForm getPropertylocalityList(TableForm tableFrom);
 
       public List<SelectCombo> getPropertylocalityComboList(HttpServletRequest request);
}
