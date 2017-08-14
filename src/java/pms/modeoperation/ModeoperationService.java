/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.modeoperation;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Modeoperation Vision
 */
public interface ModeoperationService {
    
     void addModeoperation(ModeoperationForm modeoperationForm);
    void updateModeoperation(ModeoperationForm modeoperationForm);
    void deleteModeoperation(Integer id);
    TableForm getModeoperationList(TableForm taleform);
 
    public ModeoperationForm editModeoperation(Integer id);
    
        public List<SelectCombo> getModeoperationComboList(HttpServletRequest request);
}
