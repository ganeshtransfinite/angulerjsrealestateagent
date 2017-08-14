/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertytype;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertytypeDAO {
 
    void addPropertytype(PropertytypeForm propertytypeForm);
    public PropertytypeForm editPropertytype(Integer id);
    void updatePropertytype(PropertytypeForm propertytypeForm);
    void deletePropertytype(Integer id);
    TableForm getPropertytypeList(TableForm tableFrom);
 
       public List<SelectCombo> getPropertytypeComboList(HttpServletRequest request);
}
