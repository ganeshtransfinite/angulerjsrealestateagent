/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.customer;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Customer Vision
 */
public interface CustomerService {
    
     void addCustomer(CustomerForm customerForm);
    void updateCustomer(CustomerForm customerForm);
    void deleteCustomer(Integer id);
    TableForm getCustomerList(TableForm taleform);
 
    public CustomerForm editCustomer(Integer id);
     public boolean checkExist(String col, String val, int id);
        public List<SelectCombo> getCustomerComboList(HttpServletRequest request);
}
