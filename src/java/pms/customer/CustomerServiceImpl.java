package pms.customer;
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
public class CustomerServiceImpl implements CustomerService {
    @Autowired(required = true)
    CustomerDAO customerDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCustomer(CustomerForm customerForm) {
        customerDAO.addCustomer(customerForm);
    }
    @Override
    @Transactional
    public TableForm getCustomerList(TableForm tableform) {
        return customerDAO.getCustomerList(tableform);
    }
    @Override
    @Transactional
    public CustomerForm editCustomer(Integer id) {
        return customerDAO.editCustomer(id);
    }
    @Override
    @Transactional
    public void updateCustomer(CustomerForm customerForm) {
        customerDAO.updateCustomer(customerForm);
    }
    @Override
    @Transactional
    public void deleteCustomer(Integer id) {
        customerDAO.deleteCustomer(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getCustomerComboList(HttpServletRequest request) {
        return customerDAO.getCustomerComboList(request);
    }
    
     @Override
    @Transactional
     public boolean checkExist(String col, String val, int id){
         return customerDAO.checkExist(col, val, id);//request);
     }
}
