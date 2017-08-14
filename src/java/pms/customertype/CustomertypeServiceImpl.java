package pms.customertype;
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
public class CustomertypeServiceImpl implements CustomertypeService {
    @Autowired(required = true)
    CustomertypeDAO customertypeDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCustomertype(CustomertypeForm customertypeForm) {
        customertypeDAO.addCustomertype(customertypeForm);
    }
    @Override
    @Transactional
    public TableForm getCustomertypeList(TableForm tableform) {
        return customertypeDAO.getCustomertypeList(tableform);
    }
    @Override
    @Transactional
    public CustomertypeForm editCustomertype(Integer id) {
        return customertypeDAO.editCustomertype(id);
    }
    @Override
    @Transactional
    public void updateCustomertype(CustomertypeForm customertypeForm) {
        customertypeDAO.updateCustomertype(customertypeForm);
    }
    @Override
    @Transactional
    public void deleteCustomertype(Integer id) {
        customertypeDAO.deleteCustomertype(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getCustomertypeComboList(HttpServletRequest request) {
        return customertypeDAO.getCustomertypeComboList(request);
    }
}
