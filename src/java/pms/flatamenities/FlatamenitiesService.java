/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.flatamenities;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Flatamenities Vision
 */
public interface FlatamenitiesService {
    
     void addFlatamenities(FlatamenitiesForm flatamenitiesForm);
    void updateFlatamenities(FlatamenitiesForm flatamenitiesForm);
    void deleteFlatamenities(Integer id);
    TableForm getFlatamenitiesList(TableForm taleform);
 
    public FlatamenitiesForm editFlatamenities(Integer id);
    
        public List<SelectCombo> getFlatamenitiesComboList(HttpServletRequest request);
}
