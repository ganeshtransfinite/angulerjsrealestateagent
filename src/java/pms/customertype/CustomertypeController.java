package pms.customertype;
 
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
 * @customertype user
 */
@Controller
public class CustomertypeController {
     @Autowired(required = true)
    CustomertypeService customertypeService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCustomertype.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    CustomertypeForm addCustomertype(@RequestBody CustomertypeForm customertypeForm) {
        if (customertypeForm != null) {
            try {
                    
customertypeForm.setActive("1"); 
customertypeForm.setCreateddate(new Date()); 
customertypeForm.setModifyddate(new Date()); 
 if (customertypeForm.getCustomertypeid() > 0) {
                  
customertypeService.updateCustomertype(customertypeForm);
                }else{
                    customertypeService.addCustomertype(customertypeForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Customertype  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return customertypeForm;
    }
    
    @RequestMapping(value = "/editcustomertype.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    CustomertypeForm editCustomertypedata(@RequestBody final CustomertypeForm customertypeForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CustomertypeForm customertypeForm1 = customertypeService.editCustomertype(customertypeForm.getCustomertypeid());
        return customertypeForm1;
    }
    @RequestMapping(value = "/getcustomertypelist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getCustomertypeList(@RequestBody final TableForm customertypeForm) throws Exception {
        customertypeService.getCustomertypeList(customertypeForm);
        return customertypeForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletecustomertype.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteCustomertype(@RequestBody final String customertypeForm) throws Exception {
       String str[]=customertypeForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    customertypeService.deleteCustomertype(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getCustomertypeFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getCustomertypecomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> customertypelist = customertypeService.getCustomertypeComboList( request);
        return customertypelist;
    }
}
 
