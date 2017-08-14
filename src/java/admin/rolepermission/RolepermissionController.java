package admin.rolepermission;
 
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
 * @rolepermission user
 */
@Controller
public class RolepermissionController {
     @Autowired(required = true)
    RolepermissionService rolepermissionService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveRolepermission.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    RolepermissionForm addRolepermission(@RequestBody RolepermissionForm rolepermissionForm) {
        if (rolepermissionForm != null) {
            try {
                    
rolepermissionForm.setActive("1"); 
rolepermissionForm.setCreateddate(new Date()); 
rolepermissionForm.setModifyddate(new Date()); 
 if (rolepermissionForm.getRolepermissionid() > 0) {
                  
rolepermissionService.updateRolepermission(rolepermissionForm);
                }else{
                    rolepermissionService.addRolepermission(rolepermissionForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Rolepermission  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return rolepermissionForm;
    }
    
    @RequestMapping(value = "/editrolepermission.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    RolepermissionForm editRolepermissiondata(@RequestBody final RolepermissionForm rolepermissionForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        RolepermissionForm rolepermissionForm1 = rolepermissionService.editRolepermission(rolepermissionForm.getRolepermissionid());
        return rolepermissionForm1;
    }
    @RequestMapping(value = "/getrolepermissionlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getRolepermissionList(@RequestBody final TableForm rolepermissionForm) throws Exception {
        rolepermissionService.getRolepermissionList(rolepermissionForm);
        return rolepermissionForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleterolepermission.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteRolepermission(@RequestBody final String rolepermissionForm) throws Exception {
       String str[]=rolepermissionForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    rolepermissionService.deleteRolepermission(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getRolepermissionFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getRolepermissioncomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> rolepermissionlist = rolepermissionService.getRolepermissionComboList( request);
        return rolepermissionlist;
    }
    
    @RequestMapping(value = "/getRolepermissionFormColUpdate.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    void getRolepermissionFormColUpdate(HttpServletRequest request) throws Exception {

        rolepermissionService.updateRolepermission(Integer.parseInt(request.getParameter("id")), Boolean.parseBoolean(request.getParameter("val")),
                request.getParameter("col"));

    }
}
 
