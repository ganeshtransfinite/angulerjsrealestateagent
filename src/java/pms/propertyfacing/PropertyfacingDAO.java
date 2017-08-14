/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertyfacing;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertyfacingDAO {
 
    void addPropertyfacing(PropertyfacingForm propertyfacingForm);
    public PropertyfacingForm editPropertyfacing(Integer id);
    void updatePropertyfacing(PropertyfacingForm propertyfacingForm);
    void deletePropertyfacing(Integer id);
    TableForm getPropertyfacingList(TableForm tableFrom);
 
       public List<SelectCombo> getPropertyfacingComboList(HttpServletRequest request);
}
