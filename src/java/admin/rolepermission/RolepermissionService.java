/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.rolepermission;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Rolepermission Vision
 */
public interface RolepermissionService {
    
     void addRolepermission(RolepermissionForm rolepermissionForm);
    void updateRolepermission(RolepermissionForm rolepermissionForm);
    void deleteRolepermission(Integer id);
    TableForm getRolepermissionList(TableForm taleform);
 
    public RolepermissionForm editRolepermission(Integer id);
     public void updateRolepermission(int id, boolean val, String col);
        public List<SelectCombo> getRolepermissionComboList(HttpServletRequest request);
}
