package pms.societyamenities;
 
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
 * @societyamenities user
 */
@Controller
public class SocietyamenitiesController {
     @Autowired(required = true)
    SocietyamenitiesService societyamenitiesService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveSocietyamenities.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    SocietyamenitiesForm addSocietyamenities(@RequestBody SocietyamenitiesForm societyamenitiesForm) {
        if (societyamenitiesForm != null) {
            try {
                    
societyamenitiesForm.setActive("1"); 
societyamenitiesForm.setCreateddate(new Date()); 
societyamenitiesForm.setModifyddate(new Date()); 
 if (societyamenitiesForm.getSocietyamenitiesid() > 0) {
                  
societyamenitiesService.updateSocietyamenities(societyamenitiesForm);
                }else{
                    societyamenitiesService.addSocietyamenities(societyamenitiesForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Societyamenities  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return societyamenitiesForm;
    }
    
    @RequestMapping(value = "/editsocietyamenities.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    SocietyamenitiesForm editSocietyamenitiesdata(@RequestBody final SocietyamenitiesForm societyamenitiesForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        SocietyamenitiesForm societyamenitiesForm1 = societyamenitiesService.editSocietyamenities(societyamenitiesForm.getSocietyamenitiesid());
        return societyamenitiesForm1;
    }
    @RequestMapping(value = "/getsocietyamenitieslist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getSocietyamenitiesList(@RequestBody final TableForm societyamenitiesForm) throws Exception {
        societyamenitiesService.getSocietyamenitiesList(societyamenitiesForm);
        return societyamenitiesForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletesocietyamenities.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteSocietyamenities(@RequestBody final String societyamenitiesForm) throws Exception {
       String str[]=societyamenitiesForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    societyamenitiesService.deleteSocietyamenities(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getSocietyamenitiesFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getSocietyamenitiescomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> societyamenitieslist = societyamenitiesService.getSocietyamenitiesComboList( request);
        return societyamenitieslist;
    }
}
 
