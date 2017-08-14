package pms.propertyfurnished;
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
public class PropertyfurnishedServiceImpl implements PropertyfurnishedService {
    @Autowired(required = true)
    PropertyfurnishedDAO propertyfurnishedDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPropertyfurnished(PropertyfurnishedForm propertyfurnishedForm) {
        propertyfurnishedDAO.addPropertyfurnished(propertyfurnishedForm);
    }
    @Override
    @Transactional
    public TableForm getPropertyfurnishedList(TableForm tableform) {
        return propertyfurnishedDAO.getPropertyfurnishedList(tableform);
    }
    @Override
    @Transactional
    public PropertyfurnishedForm editPropertyfurnished(Integer id) {
        return propertyfurnishedDAO.editPropertyfurnished(id);
    }
    @Override
    @Transactional
    public void updatePropertyfurnished(PropertyfurnishedForm propertyfurnishedForm) {
        propertyfurnishedDAO.updatePropertyfurnished(propertyfurnishedForm);
    }
    @Override
    @Transactional
    public void deletePropertyfurnished(Integer id) {
        propertyfurnishedDAO.deletePropertyfurnished(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getPropertyfurnishedComboList(HttpServletRequest request) {
        return propertyfurnishedDAO.getPropertyfurnishedComboList(request);
    }
}
