package pms.city;
import admin.filter.SelectCombo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import admin.filter.TableForm;
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CityServiceImpl implements CityService {
    @Autowired(required = true)
    CityDAO cityDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCity(CityForm cityForm) {
        cityDAO.addCity(cityForm);
    }
    @Override
    @Transactional
    public TableForm getCityList(TableForm tableform) {
        return cityDAO.getCityList(tableform);
    }
    @Override
    @Transactional
    public CityForm editCity(Integer id) {
        return cityDAO.editCity(id);
    }
    @Override
    @Transactional
    public void updateCity(CityForm cityForm) {
        cityDAO.updateCity(cityForm);
    }
    @Override
    @Transactional
    public void deleteCity(Integer id) {
        cityDAO.deleteCity(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getCityComboList(HttpServletRequest request) {
        return cityDAO.getCityComboList(request);
    }
}
