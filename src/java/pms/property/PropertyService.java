/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.property;

import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Property Vision
 */
public interface PropertyService {
public boolean duplicate(PropertyForm propertyForm);
    void addProperty(PropertyForm propertyForm);

    void updateProperty(PropertyForm propertyForm);

    void deleteProperty(Integer id);

    TableForm getPropertyList(TableForm taleform);

    public void updateALLProperty();

    public PropertyForm editProperty(Integer id);

    public List<SelectCombo> getPropertyComboList(HttpServletRequest request);
}
