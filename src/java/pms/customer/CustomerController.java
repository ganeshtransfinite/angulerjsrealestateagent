package pms.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jr.ob.JSON;
import admin.filter.SelectCombo;
import admin.filter.TableForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @customer user
 */
@Controller
public class CustomerController {

    @Autowired(required = true)
    CustomerService customerService;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCustomer.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    CustomerForm addCustomer(@RequestBody CustomerForm customerForm) {
        if (customerForm != null) {
            try {

                customerForm.setActive("1");
                customerForm.setCreateddate(new Date());
                customerForm.setModifyddate(new Date());
                if (customerForm.getCustomerid() > 0) {

                    customerService.updateCustomer(customerForm);
                } else {
                    customerService.addCustomer(customerForm);
                }

                //              model.addAttribute("msg", "Succesfully Customer  Saved.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return customerForm;
    }

    @RequestMapping(value = "/editcustomer.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    CustomerForm editCustomerdata(@RequestBody final CustomerForm customerForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CustomerForm customerForm1 = customerService.editCustomer(customerForm.getCustomerid());
        return customerForm1;
    }

    @RequestMapping(value = "/getcustomerlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getCustomerList(@RequestBody final TableForm customerForm) throws Exception {
        customerService.getCustomerList(customerForm);
        return customerForm;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletecustomer.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteCustomer(@RequestBody final String customerForm) throws Exception {
        String str[] = customerForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    customerService.deleteCustomer(Integer.parseInt(str[i]));
                }

                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/getCustomerFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getCustomercomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> customerlist = customerService.getCustomerComboList(request);
        return customerlist;
    }

    @RequestMapping(value = "/checkExistCustomer.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    boolean checkExistCustomer(HttpServletRequest request) throws Exception {

        return customerService.checkExist(request.getParameter("col"), request.getParameter("val"), Integer.parseInt(request.getParameter("id")));

    }
}
