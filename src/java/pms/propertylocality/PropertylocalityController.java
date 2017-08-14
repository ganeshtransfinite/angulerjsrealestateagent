package pms.propertylocality;
 
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
 * @propertylocality user
 */
@Controller
public class PropertylocalityController {
     @Autowired(required = true)
    PropertylocalityService propertylocalityService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePropertylocality.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertylocalityForm addPropertylocality(@RequestBody PropertylocalityForm propertylocalityForm) {
        if (propertylocalityForm != null) {
            try {
                    
propertylocalityForm.setActive("1"); 
propertylocalityForm.setCreateddate(new Date()); 
propertylocalityForm.setModifyddate(new Date()); 
 if (propertylocalityForm.getPropertylocalityid() > 0) {
                  
propertylocalityService.updatePropertylocality(propertylocalityForm);
                }else{
                    propertylocalityService.addPropertylocality(propertylocalityForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Propertylocality  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return propertylocalityForm;
    }
    
    @RequestMapping(value = "/editpropertylocality.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertylocalityForm editPropertylocalitydata(@RequestBody final PropertylocalityForm propertylocalityForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertylocalityForm propertylocalityForm1 = propertylocalityService.editPropertylocality(propertylocalityForm.getPropertylocalityid());
        return propertylocalityForm1;
    }
    @RequestMapping(value = "/getpropertylocalitylist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertylocalityList(@RequestBody final TableForm propertylocalityForm) throws Exception {
        propertylocalityService.getPropertylocalityList(propertylocalityForm);
        return propertylocalityForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletepropertylocality.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deletePropertylocality(@RequestBody final String propertylocalityForm) throws Exception {
       String str[]=propertylocalityForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertylocalityService.deletePropertylocality(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getPropertylocalityFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertylocalitycomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertylocalitylist = propertylocalityService.getPropertylocalityComboList( request);
        return propertylocalitylist;
    }
}
 
