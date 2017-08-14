package pms.property;

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
public class PropertyServiceImpl implements PropertyService {

    @Autowired(required = true)
    PropertyDAO propertyDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addProperty(PropertyForm propertyForm) {
        propertyDAO.addProperty(propertyForm);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean duplicate(PropertyForm propertyForm) {
        return propertyDAO.duplicate(propertyForm);
    }

    @Override
    @Transactional
    public TableForm getPropertyList(TableForm tableform) {
        return propertyDAO.getPropertyList(tableform);
    }

    @Override
    @Transactional
    public PropertyForm editProperty(Integer id) {
        return propertyDAO.editProperty(id);
    }

    @Override
    @Transactional
    public void updateProperty(PropertyForm propertyForm) {
        propertyDAO.updateProperty(propertyForm);
    }

    @Override
    @Transactional
    public void deleteProperty(Integer id) {
        propertyDAO.deleteProperty(id);
    }

    @Override
    @Transactional
    public void updateALLProperty() {
        propertyDAO.updateALLProperty();
    }

    @Override
    @Transactional
    public List<SelectCombo> getPropertyComboList(HttpServletRequest request) {
        return propertyDAO.getPropertyComboList(request);
    }
}
