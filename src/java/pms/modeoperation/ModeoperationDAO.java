/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.modeoperation;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface ModeoperationDAO {
 
    void addModeoperation(ModeoperationForm modeoperationForm);
    public ModeoperationForm editModeoperation(Integer id);
    void updateModeoperation(ModeoperationForm modeoperationForm);
    void deleteModeoperation(Integer id);
    TableForm getModeoperationList(TableForm tableFrom);
 
       public List<SelectCombo> getModeoperationComboList(HttpServletRequest request);
}
