package pms.gender;
 
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
 * @gender user
 */
@Controller
public class GenderController {
     @Autowired(required = true)
    GenderService genderService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveGender.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    GenderForm addGender(@RequestBody GenderForm genderForm) {
        if (genderForm != null) {
            try {
                    
genderForm.setActive("1"); 
genderForm.setCreateddate(new Date()); 
genderForm.setModifyddate(new Date()); 
 if (genderForm.getGenderid() > 0) {
                  
genderService.updateGender(genderForm);
                }else{
                    genderService.addGender(genderForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Gender  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return genderForm;
    }
    
    @RequestMapping(value = "/editgender.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    GenderForm editGenderdata(@RequestBody final GenderForm genderForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        GenderForm genderForm1 = genderService.editGender(genderForm.getGenderid());
        return genderForm1;
    }
    @RequestMapping(value = "/getgenderlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getGenderList(@RequestBody final TableForm genderForm) throws Exception {
        genderService.getGenderList(genderForm);
        return genderForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletegender.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteGender(@RequestBody final String genderForm) throws Exception {
       String str[]=genderForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    genderService.deleteGender(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getGenderFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getGendercomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> genderlist = genderService.getGenderComboList( request);
        return genderlist;
    }
}
 
