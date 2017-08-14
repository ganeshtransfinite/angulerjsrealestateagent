package pms.propertytobe;
 
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
 * @propertytobe user
 */
@Controller
public class PropertytobeController {
     @Autowired(required = true)
    PropertytobeService propertytobeService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePropertytobe.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertytobeForm addPropertytobe(@RequestBody PropertytobeForm propertytobeForm) {
        if (propertytobeForm != null) {
            try {
                    
propertytobeForm.setActive("1"); 
propertytobeForm.setCreateddate(new Date()); 
propertytobeForm.setModifyddate(new Date()); 
 if (propertytobeForm.getPropertytobeid() > 0) {
                  
propertytobeService.updatePropertytobe(propertytobeForm);
                }else{
                    propertytobeService.addPropertytobe(propertytobeForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Propertytobe  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return propertytobeForm;
    }
    
    @RequestMapping(value = "/editpropertytobe.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertytobeForm editPropertytobedata(@RequestBody final PropertytobeForm propertytobeForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertytobeForm propertytobeForm1 = propertytobeService.editPropertytobe(propertytobeForm.getPropertytobeid());
        return propertytobeForm1;
    }
    @RequestMapping(value = "/getpropertytobelist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertytobeList(@RequestBody final TableForm propertytobeForm) throws Exception {
        propertytobeService.getPropertytobeList(propertytobeForm);
        return propertytobeForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletepropertytobe.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deletePropertytobe(@RequestBody final String propertytobeForm) throws Exception {
       String str[]=propertytobeForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertytobeService.deletePropertytobe(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getPropertytobeFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertytobecomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertytobelist = propertytobeService.getPropertytobeComboList( request);
        return propertytobelist;
    }
}
 
