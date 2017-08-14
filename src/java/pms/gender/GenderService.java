/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.gender;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Gender Vision
 */
public interface GenderService {
    
     void addGender(GenderForm genderForm);
    void updateGender(GenderForm genderForm);
    void deleteGender(Integer id);
    TableForm getGenderList(TableForm taleform);
 
    public GenderForm editGender(Integer id);
    
        public List<SelectCombo> getGenderComboList(HttpServletRequest request);
}
