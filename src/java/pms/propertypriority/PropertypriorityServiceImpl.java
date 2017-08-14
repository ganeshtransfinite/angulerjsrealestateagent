package pms.propertypriority;
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
public class PropertypriorityServiceImpl implements PropertypriorityService {
    @Autowired(required = true)
    PropertypriorityDAO propertypriorityDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPropertypriority(PropertypriorityForm propertypriorityForm) {
        propertypriorityDAO.addPropertypriority(propertypriorityForm);
    }
    @Override
    @Transactional
    public TableForm getPropertypriorityList(TableForm tableform) {
        return propertypriorityDAO.getPropertypriorityList(tableform);
    }
    @Override
    @Transactional
    public PropertypriorityForm editPropertypriority(Integer id) {
        return propertypriorityDAO.editPropertypriority(id);
    }
    @Override
    @Transactional
    public void updatePropertypriority(PropertypriorityForm propertypriorityForm) {
        propertypriorityDAO.updatePropertypriority(propertypriorityForm);
    }
    @Override
    @Transactional
    public void deletePropertypriority(Integer id) {
        propertypriorityDAO.deletePropertypriority(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getPropertypriorityComboList(HttpServletRequest request) {
        return propertypriorityDAO.getPropertypriorityComboList(request);
    }
}
