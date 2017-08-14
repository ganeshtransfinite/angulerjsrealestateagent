/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.flatamenities;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface FlatamenitiesDAO {
 
    void addFlatamenities(FlatamenitiesForm flatamenitiesForm);
    public FlatamenitiesForm editFlatamenities(Integer id);
    void updateFlatamenities(FlatamenitiesForm flatamenitiesForm);
    void deleteFlatamenities(Integer id);
    TableForm getFlatamenitiesList(TableForm tableFrom);
 
       public List<SelectCombo> getFlatamenitiesComboList(HttpServletRequest request);
}
