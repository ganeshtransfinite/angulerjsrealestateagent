package pms.propertytype;
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
public class PropertytypeServiceImpl implements PropertytypeService {
    @Autowired(required = true)
    PropertytypeDAO propertytypeDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPropertytype(PropertytypeForm propertytypeForm) {
        propertytypeDAO.addPropertytype(propertytypeForm);
    }
    @Override
    @Transactional
    public TableForm getPropertytypeList(TableForm tableform) {
        return propertytypeDAO.getPropertytypeList(tableform);
    }
    @Override
    @Transactional
    public PropertytypeForm editPropertytype(Integer id) {
        return propertytypeDAO.editPropertytype(id);
    }
    @Override
    @Transactional
    public void updatePropertytype(PropertytypeForm propertytypeForm) {
        propertytypeDAO.updatePropertytype(propertytypeForm);
    }
    @Override
    @Transactional
    public void deletePropertytype(Integer id) {
        propertytypeDAO.deletePropertytype(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getPropertytypeComboList(HttpServletRequest request) {
        return propertytypeDAO.getPropertytypeComboList(request);
    }
}
