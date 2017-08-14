/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.city;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface CityDAO {
 
    void addCity(CityForm cityForm);
    public CityForm editCity(Integer id);
    void updateCity(CityForm cityForm);
    void deleteCity(Integer id);
    TableForm getCityList(TableForm tableFrom);
 
       public List<SelectCombo> getCityComboList(HttpServletRequest request);
}
