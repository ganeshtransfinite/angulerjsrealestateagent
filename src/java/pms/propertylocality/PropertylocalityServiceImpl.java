package pms.propertylocality;
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
public class PropertylocalityServiceImpl implements PropertylocalityService {
    @Autowired(required = true)
    PropertylocalityDAO propertylocalityDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPropertylocality(PropertylocalityForm propertylocalityForm) {
        propertylocalityDAO.addPropertylocality(propertylocalityForm);
    }
    @Override
    @Transactional
    public TableForm getPropertylocalityList(TableForm tableform) {
        return propertylocalityDAO.getPropertylocalityList(tableform);
    }
    @Override
    @Transactional
    public PropertylocalityForm editPropertylocality(Integer id) {
        return propertylocalityDAO.editPropertylocality(id);
    }
    @Override
    @Transactional
    public void updatePropertylocality(PropertylocalityForm propertylocalityForm) {
        propertylocalityDAO.updatePropertylocality(propertylocalityForm);
    }
    @Override
    @Transactional
    public void deletePropertylocality(Integer id) {
        propertylocalityDAO.deletePropertylocality(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getPropertylocalityComboList(HttpServletRequest request) {
        return propertylocalityDAO.getPropertylocalityComboList(request);
    }
}
