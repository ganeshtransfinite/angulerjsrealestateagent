/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertytobe;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertytobeDAO {
 
    void addPropertytobe(PropertytobeForm propertytobeForm);
    public PropertytobeForm editPropertytobe(Integer id);
    void updatePropertytobe(PropertytobeForm propertytobeForm);
    void deletePropertytobe(Integer id);
    TableForm getPropertytobeList(TableForm tableFrom);
 
       public List<SelectCombo> getPropertytobeComboList(HttpServletRequest request);
}
