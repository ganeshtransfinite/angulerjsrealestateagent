package pms.propertylbedrooms;
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
public class PropertylbedroomsServiceImpl implements PropertylbedroomsService {
    @Autowired(required = true)
    PropertylbedroomsDAO propertylbedroomsDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPropertylbedrooms(PropertylbedroomsForm propertylbedroomsForm) {
        propertylbedroomsDAO.addPropertylbedrooms(propertylbedroomsForm);
    }
    @Override
    @Transactional
    public TableForm getPropertylbedroomsList(TableForm tableform) {
        return propertylbedroomsDAO.getPropertylbedroomsList(tableform);
    }
    @Override
    @Transactional
    public PropertylbedroomsForm editPropertylbedrooms(Integer id) {
        return propertylbedroomsDAO.editPropertylbedrooms(id);
    }
    @Override
    @Transactional
    public void updatePropertylbedrooms(PropertylbedroomsForm propertylbedroomsForm) {
        propertylbedroomsDAO.updatePropertylbedrooms(propertylbedroomsForm);
    }
    @Override
    @Transactional
    public void deletePropertylbedrooms(Integer id) {
        propertylbedroomsDAO.deletePropertylbedrooms(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getPropertylbedroomsComboList(HttpServletRequest request) {
        return propertylbedroomsDAO.getPropertylbedroomsComboList(request);
    }
}
