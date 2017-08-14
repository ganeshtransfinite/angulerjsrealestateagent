package pms.propertytobe;
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
public class PropertytobeServiceImpl implements PropertytobeService {
    @Autowired(required = true)
    PropertytobeDAO propertytobeDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPropertytobe(PropertytobeForm propertytobeForm) {
        propertytobeDAO.addPropertytobe(propertytobeForm);
    }
    @Override
    @Transactional
    public TableForm getPropertytobeList(TableForm tableform) {
        return propertytobeDAO.getPropertytobeList(tableform);
    }
    @Override
    @Transactional
    public PropertytobeForm editPropertytobe(Integer id) {
        return propertytobeDAO.editPropertytobe(id);
    }
    @Override
    @Transactional
    public void updatePropertytobe(PropertytobeForm propertytobeForm) {
        propertytobeDAO.updatePropertytobe(propertytobeForm);
    }
    @Override
    @Transactional
    public void deletePropertytobe(Integer id) {
        propertytobeDAO.deletePropertytobe(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getPropertytobeComboList(HttpServletRequest request) {
        return propertytobeDAO.getPropertytobeComboList(request);
    }
}
