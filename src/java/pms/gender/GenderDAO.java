/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.gender;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface GenderDAO {
 
    void addGender(GenderForm genderForm);
    public GenderForm editGender(Integer id);
    void updateGender(GenderForm genderForm);
    void deleteGender(Integer id);
    TableForm getGenderList(TableForm tableFrom);
 
       public List<SelectCombo> getGenderComboList(HttpServletRequest request);
}
