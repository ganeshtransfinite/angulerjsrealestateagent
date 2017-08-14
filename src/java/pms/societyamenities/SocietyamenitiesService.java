/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.societyamenities;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Societyamenities Vision
 */
public interface SocietyamenitiesService {
    
     void addSocietyamenities(SocietyamenitiesForm societyamenitiesForm);
    void updateSocietyamenities(SocietyamenitiesForm societyamenitiesForm);
    void deleteSocietyamenities(Integer id);
    TableForm getSocietyamenitiesList(TableForm taleform);
 
    public SocietyamenitiesForm editSocietyamenities(Integer id);
    
        public List<SelectCombo> getSocietyamenitiesComboList(HttpServletRequest request);
}
