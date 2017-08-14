/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.rolepermission;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface RolepermissionDAO {
 
    void addRolepermission(RolepermissionForm rolepermissionForm);
    public RolepermissionForm editRolepermission(Integer id);
    void updateRolepermission(RolepermissionForm rolepermissionForm);
    void deleteRolepermission(Integer id);
    TableForm getRolepermissionList(TableForm tableFrom);
  public void updateRolepermission(int id, boolean val, String col);
       public List<SelectCombo> getRolepermissionComboList(HttpServletRequest request);
}
