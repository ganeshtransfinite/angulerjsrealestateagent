package pms.propertylbedrooms;
 
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
 * @propertylbedrooms user
 */
@Controller
public class PropertylbedroomsController {
     @Autowired(required = true)
    PropertylbedroomsService propertylbedroomsService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePropertylbedrooms.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertylbedroomsForm addPropertylbedrooms(@RequestBody PropertylbedroomsForm propertylbedroomsForm) {
        if (propertylbedroomsForm != null) {
            try {
                    
propertylbedroomsForm.setActive("1"); 
propertylbedroomsForm.setCreateddate(new Date()); 
propertylbedroomsForm.setModifyddate(new Date()); 
 if (propertylbedroomsForm.getPropertylbedroomsid() > 0) {
                  
propertylbedroomsService.updatePropertylbedrooms(propertylbedroomsForm);
                }else{
                    propertylbedroomsService.addPropertylbedrooms(propertylbedroomsForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Propertylbedrooms  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return propertylbedroomsForm;
    }
    
    @RequestMapping(value = "/editpropertylbedrooms.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertylbedroomsForm editPropertylbedroomsdata(@RequestBody final PropertylbedroomsForm propertylbedroomsForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertylbedroomsForm propertylbedroomsForm1 = propertylbedroomsService.editPropertylbedrooms(propertylbedroomsForm.getPropertylbedroomsid());
        return propertylbedroomsForm1;
    }
    @RequestMapping(value = "/getpropertylbedroomslist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertylbedroomsList(@RequestBody final TableForm propertylbedroomsForm) throws Exception {
        propertylbedroomsService.getPropertylbedroomsList(propertylbedroomsForm);
        return propertylbedroomsForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletepropertylbedrooms.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deletePropertylbedrooms(@RequestBody final String propertylbedroomsForm) throws Exception {
       String str[]=propertylbedroomsForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertylbedroomsService.deletePropertylbedrooms(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getPropertylbedroomsFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertylbedroomscomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertylbedroomslist = propertylbedroomsService.getPropertylbedroomsComboList( request);
        return propertylbedroomslist;
    }
}
 
