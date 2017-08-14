/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.property;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertyDAO {
 public boolean duplicate(PropertyForm propertyForm);
    void addProperty(PropertyForm propertyForm);
    public PropertyForm editProperty(Integer id);
    void updateProperty(PropertyForm propertyForm);
    void deleteProperty(Integer id);
    TableForm getPropertyList(TableForm tableFrom);
  public void updateALLProperty();
       public List<SelectCombo> getPropertyComboList(HttpServletRequest request);
}
