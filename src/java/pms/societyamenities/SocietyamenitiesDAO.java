/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.societyamenities;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface SocietyamenitiesDAO {
 
    void addSocietyamenities(SocietyamenitiesForm societyamenitiesForm);
    public SocietyamenitiesForm editSocietyamenities(Integer id);
    void updateSocietyamenities(SocietyamenitiesForm societyamenitiesForm);
    void deleteSocietyamenities(Integer id);
    TableForm getSocietyamenitiesList(TableForm tableFrom);
 
       public List<SelectCombo> getSocietyamenitiesComboList(HttpServletRequest request);
}
