/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.occupation;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Occupation Vision
 */
public interface OccupationService {
    
     void addOccupation(OccupationForm occupationForm);
    void updateOccupation(OccupationForm occupationForm);
    void deleteOccupation(Integer id);
    TableForm getOccupationList(TableForm taleform);
 
    public OccupationForm editOccupation(Integer id);
    
        public List<SelectCombo> getOccupationComboList(HttpServletRequest request);
}
