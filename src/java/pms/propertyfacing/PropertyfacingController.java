package pms.propertyfacing;
 
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
 * @propertyfacing user
 */
@Controller
public class PropertyfacingController {
     @Autowired(required = true)
    PropertyfacingService propertyfacingService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePropertyfacing.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertyfacingForm addPropertyfacing(@RequestBody PropertyfacingForm propertyfacingForm) {
        if (propertyfacingForm != null) {
            try {
                    
propertyfacingForm.setActive("1"); 
propertyfacingForm.setCreateddate(new Date()); 
propertyfacingForm.setModifyddate(new Date()); 
 if (propertyfacingForm.getPropertyfacingid() > 0) {
                  
propertyfacingService.updatePropertyfacing(propertyfacingForm);
                }else{
                    propertyfacingService.addPropertyfacing(propertyfacingForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Propertyfacing  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return propertyfacingForm;
    }
    
    @RequestMapping(value = "/editpropertyfacing.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertyfacingForm editPropertyfacingdata(@RequestBody final PropertyfacingForm propertyfacingForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertyfacingForm propertyfacingForm1 = propertyfacingService.editPropertyfacing(propertyfacingForm.getPropertyfacingid());
        return propertyfacingForm1;
    }
    @RequestMapping(value = "/getpropertyfacinglist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertyfacingList(@RequestBody final TableForm propertyfacingForm) throws Exception {
        propertyfacingService.getPropertyfacingList(propertyfacingForm);
        return propertyfacingForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletepropertyfacing.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deletePropertyfacing(@RequestBody final String propertyfacingForm) throws Exception {
       String str[]=propertyfacingForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertyfacingService.deletePropertyfacing(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getPropertyfacingFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertyfacingcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertyfacinglist = propertyfacingService.getPropertyfacingComboList( request);
        return propertyfacinglist;
    }
}
 
