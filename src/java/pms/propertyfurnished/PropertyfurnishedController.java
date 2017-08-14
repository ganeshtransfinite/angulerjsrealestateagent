package pms.propertyfurnished;
 
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
 * @propertyfurnished user
 */
@Controller
public class PropertyfurnishedController {
     @Autowired(required = true)
    PropertyfurnishedService propertyfurnishedService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePropertyfurnished.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertyfurnishedForm addPropertyfurnished(@RequestBody PropertyfurnishedForm propertyfurnishedForm) {
        if (propertyfurnishedForm != null) {
            try {
                    
propertyfurnishedForm.setActive("1"); 
propertyfurnishedForm.setCreateddate(new Date()); 
propertyfurnishedForm.setModifyddate(new Date()); 
 if (propertyfurnishedForm.getPropertyfurnishedid() > 0) {
                  
propertyfurnishedService.updatePropertyfurnished(propertyfurnishedForm);
                }else{
                    propertyfurnishedService.addPropertyfurnished(propertyfurnishedForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Propertyfurnished  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return propertyfurnishedForm;
    }
    
    @RequestMapping(value = "/editpropertyfurnished.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertyfurnishedForm editPropertyfurnisheddata(@RequestBody final PropertyfurnishedForm propertyfurnishedForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertyfurnishedForm propertyfurnishedForm1 = propertyfurnishedService.editPropertyfurnished(propertyfurnishedForm.getPropertyfurnishedid());
        return propertyfurnishedForm1;
    }
    @RequestMapping(value = "/getpropertyfurnishedlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertyfurnishedList(@RequestBody final TableForm propertyfurnishedForm) throws Exception {
        propertyfurnishedService.getPropertyfurnishedList(propertyfurnishedForm);
        return propertyfurnishedForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletepropertyfurnished.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deletePropertyfurnished(@RequestBody final String propertyfurnishedForm) throws Exception {
       String str[]=propertyfurnishedForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertyfurnishedService.deletePropertyfurnished(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getPropertyfurnishedFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertyfurnishedcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertyfurnishedlist = propertyfurnishedService.getPropertyfurnishedComboList( request);
        return propertyfurnishedlist;
    }
}
 
