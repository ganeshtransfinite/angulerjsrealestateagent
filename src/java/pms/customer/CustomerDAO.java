/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.customer;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface CustomerDAO {
 
    void addCustomer(CustomerForm customerForm);
    public CustomerForm editCustomer(Integer id);
    void updateCustomer(CustomerForm customerForm);
    void deleteCustomer(Integer id);
    TableForm getCustomerList(TableForm tableFrom);
  public boolean checkExist(String col, String val, int id);
       public List<SelectCombo> getCustomerComboList(HttpServletRequest request);
}
