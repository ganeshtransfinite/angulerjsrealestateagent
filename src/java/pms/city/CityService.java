/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.city;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @City Vision
 */
public interface CityService {
    
     void addCity(CityForm cityForm);
    void updateCity(CityForm cityForm);
    void deleteCity(Integer id);
    TableForm getCityList(TableForm taleform);
 
    public CityForm editCity(Integer id);
    
        public List<SelectCombo> getCityComboList(HttpServletRequest request);
}
