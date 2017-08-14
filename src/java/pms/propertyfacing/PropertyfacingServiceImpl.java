package pms.propertyfacing;
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
public class PropertyfacingServiceImpl implements PropertyfacingService {
    @Autowired(required = true)
    PropertyfacingDAO propertyfacingDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPropertyfacing(PropertyfacingForm propertyfacingForm) {
        propertyfacingDAO.addPropertyfacing(propertyfacingForm);
    }
    @Override
    @Transactional
    public TableForm getPropertyfacingList(TableForm tableform) {
        return propertyfacingDAO.getPropertyfacingList(tableform);
    }
    @Override
    @Transactional
    public PropertyfacingForm editPropertyfacing(Integer id) {
        return propertyfacingDAO.editPropertyfacing(id);
    }
    @Override
    @Transactional
    public void updatePropertyfacing(PropertyfacingForm propertyfacingForm) {
        propertyfacingDAO.updatePropertyfacing(propertyfacingForm);
    }
    @Override
    @Transactional
    public void deletePropertyfacing(Integer id) {
        propertyfacingDAO.deletePropertyfacing(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getPropertyfacingComboList(HttpServletRequest request) {
        return propertyfacingDAO.getPropertyfacingComboList(request);
    }
}
