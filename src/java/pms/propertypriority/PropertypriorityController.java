package pms.propertypriority;
 
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
 * @propertypriority user
 */
@Controller
public class PropertypriorityController {
     @Autowired(required = true)
    PropertypriorityService propertypriorityService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePropertypriority.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertypriorityForm addPropertypriority(@RequestBody PropertypriorityForm propertypriorityForm) {
        if (propertypriorityForm != null) {
            try {
                    
propertypriorityForm.setActive("1"); 
propertypriorityForm.setCreateddate(new Date()); 
propertypriorityForm.setModifyddate(new Date()); 
 if (propertypriorityForm.getPropertypriorityid() > 0) {
                  
propertypriorityService.updatePropertypriority(propertypriorityForm);
                }else{
                    propertypriorityService.addPropertypriority(propertypriorityForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Propertypriority  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return propertypriorityForm;
    }
    
    @RequestMapping(value = "/editpropertypriority.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertypriorityForm editPropertyprioritydata(@RequestBody final PropertypriorityForm propertypriorityForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertypriorityForm propertypriorityForm1 = propertypriorityService.editPropertypriority(propertypriorityForm.getPropertypriorityid());
        return propertypriorityForm1;
    }
    @RequestMapping(value = "/getpropertyprioritylist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertypriorityList(@RequestBody final TableForm propertypriorityForm) throws Exception {
        propertypriorityService.getPropertypriorityList(propertypriorityForm);
        return propertypriorityForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletepropertypriority.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deletePropertypriority(@RequestBody final String propertypriorityForm) throws Exception {
       String str[]=propertypriorityForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertypriorityService.deletePropertypriority(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getPropertypriorityFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertyprioritycomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertyprioritylist = propertypriorityService.getPropertypriorityComboList( request);
        return propertyprioritylist;
    }
}
 
