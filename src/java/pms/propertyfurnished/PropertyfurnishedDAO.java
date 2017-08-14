/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.propertyfurnished;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface PropertyfurnishedDAO {
 
    void addPropertyfurnished(PropertyfurnishedForm propertyfurnishedForm);
    public PropertyfurnishedForm editPropertyfurnished(Integer id);
    void updatePropertyfurnished(PropertyfurnishedForm propertyfurnishedForm);
    void deletePropertyfurnished(Integer id);
    TableForm getPropertyfurnishedList(TableForm tableFrom);
 
       public List<SelectCombo> getPropertyfurnishedComboList(HttpServletRequest request);
}
