/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.occupation;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface OccupationDAO {
 
    void addOccupation(OccupationForm occupationForm);
    public OccupationForm editOccupation(Integer id);
    void updateOccupation(OccupationForm occupationForm);
    void deleteOccupation(Integer id);
    TableForm getOccupationList(TableForm tableFrom);
 
       public List<SelectCombo> getOccupationComboList(HttpServletRequest request);
}
