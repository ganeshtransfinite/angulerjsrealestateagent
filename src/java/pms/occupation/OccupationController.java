package pms.occupation;
 
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
 * @occupation user
 */
@Controller
public class OccupationController {
     @Autowired(required = true)
    OccupationService occupationService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveOccupation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    OccupationForm addOccupation(@RequestBody OccupationForm occupationForm) {
        if (occupationForm != null) {
            try {
                    
occupationForm.setActive("1"); 
occupationForm.setCreateddate(new Date()); 
occupationForm.setModifyddate(new Date()); 
 if (occupationForm.getOccupationid() > 0) {
                  
occupationService.updateOccupation(occupationForm);
                }else{
                    occupationService.addOccupation(occupationForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Occupation  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return occupationForm;
    }
    
    @RequestMapping(value = "/editoccupation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    OccupationForm editOccupationdata(@RequestBody final OccupationForm occupationForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        OccupationForm occupationForm1 = occupationService.editOccupation(occupationForm.getOccupationid());
        return occupationForm1;
    }
    @RequestMapping(value = "/getoccupationlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getOccupationList(@RequestBody final TableForm occupationForm) throws Exception {
        occupationService.getOccupationList(occupationForm);
        return occupationForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteoccupation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteOccupation(@RequestBody final String occupationForm) throws Exception {
       String str[]=occupationForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    occupationService.deleteOccupation(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getOccupationFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getOccupationcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> occupationlist = occupationService.getOccupationComboList( request);
        return occupationlist;
    }
}
 
