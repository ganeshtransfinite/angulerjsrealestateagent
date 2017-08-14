package pms.propertytype;
 
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
 * @propertytype user
 */
@Controller
public class PropertytypeController {
     @Autowired(required = true)
    PropertytypeService propertytypeService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/savePropertytype.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertytypeForm addPropertytype(@RequestBody PropertytypeForm propertytypeForm) {
        if (propertytypeForm != null) {
            try {
                    
propertytypeForm.setActive("1"); 
propertytypeForm.setCreateddate(new Date()); 
propertytypeForm.setModifyddate(new Date()); 
 if (propertytypeForm.getPropertytypeid() > 0) {
                  
propertytypeService.updatePropertytype(propertytypeForm);
                }else{
                    propertytypeService.addPropertytype(propertytypeForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Propertytype  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return propertytypeForm;
    }
    
    @RequestMapping(value = "/editpropertytype.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertytypeForm editPropertytypedata(@RequestBody final PropertytypeForm propertytypeForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertytypeForm propertytypeForm1 = propertytypeService.editPropertytype(propertytypeForm.getPropertytypeid());
        return propertytypeForm1;
    }
    @RequestMapping(value = "/getpropertytypelist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertytypeList(@RequestBody final TableForm propertytypeForm) throws Exception {
        propertytypeService.getPropertytypeList(propertytypeForm);
        return propertytypeForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletepropertytype.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deletePropertytype(@RequestBody final String propertytypeForm) throws Exception {
       String str[]=propertytypeForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertytypeService.deletePropertytype(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getPropertytypeFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertytypecomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertytypelist = propertytypeService.getPropertytypeComboList( request);
        return propertytypelist;
    }
}
 
