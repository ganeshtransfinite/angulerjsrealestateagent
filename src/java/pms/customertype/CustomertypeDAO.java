/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.customertype;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface CustomertypeDAO {
 
    void addCustomertype(CustomertypeForm customertypeForm);
    public CustomertypeForm editCustomertype(Integer id);
    void updateCustomertype(CustomertypeForm customertypeForm);
    void deleteCustomertype(Integer id);
    TableForm getCustomertypeList(TableForm tableFrom);
 
       public List<SelectCombo> getCustomertypeComboList(HttpServletRequest request);
}
