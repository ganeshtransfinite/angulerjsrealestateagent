package pms.flatamenities;
 
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
 * @flatamenities user
 */
@Controller
public class FlatamenitiesController {
     @Autowired(required = true)
    FlatamenitiesService flatamenitiesService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveFlatamenities.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    FlatamenitiesForm addFlatamenities(@RequestBody FlatamenitiesForm flatamenitiesForm) {
        if (flatamenitiesForm != null) {
            try {
                    
flatamenitiesForm.setActive("1"); 
flatamenitiesForm.setCreateddate(new Date()); 
flatamenitiesForm.setModifyddate(new Date()); 
 if (flatamenitiesForm.getFlatamenitiesid() > 0) {
                  
flatamenitiesService.updateFlatamenities(flatamenitiesForm);
                }else{
                    flatamenitiesService.addFlatamenities(flatamenitiesForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Flatamenities  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return flatamenitiesForm;
    }
    
    @RequestMapping(value = "/editflatamenities.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    FlatamenitiesForm editFlatamenitiesdata(@RequestBody final FlatamenitiesForm flatamenitiesForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        FlatamenitiesForm flatamenitiesForm1 = flatamenitiesService.editFlatamenities(flatamenitiesForm.getFlatamenitiesid());
        return flatamenitiesForm1;
    }
    @RequestMapping(value = "/getflatamenitieslist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getFlatamenitiesList(@RequestBody final TableForm flatamenitiesForm) throws Exception {
        flatamenitiesService.getFlatamenitiesList(flatamenitiesForm);
        return flatamenitiesForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteflatamenities.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteFlatamenities(@RequestBody final String flatamenitiesForm) throws Exception {
       String str[]=flatamenitiesForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    flatamenitiesService.deleteFlatamenities(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getFlatamenitiesFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getFlatamenitiescomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> flatamenitieslist = flatamenitiesService.getFlatamenitiesComboList( request);
        return flatamenitieslist;
    }
}
 
