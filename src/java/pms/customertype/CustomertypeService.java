/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.customertype;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Customertype Vision
 */
public interface CustomertypeService {
    
     void addCustomertype(CustomertypeForm customertypeForm);
    void updateCustomertype(CustomertypeForm customertypeForm);
    void deleteCustomertype(Integer id);
    TableForm getCustomertypeList(TableForm taleform);
 
    public CustomertypeForm editCustomertype(Integer id);
    
        public List<SelectCombo> getCustomertypeComboList(HttpServletRequest request);
}
