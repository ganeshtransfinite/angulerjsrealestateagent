package pms.modeoperation;
 
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
 * @modeoperation user
 */
@Controller
public class ModeoperationController {
     @Autowired(required = true)
    ModeoperationService modeoperationService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveModeoperation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    ModeoperationForm addModeoperation(@RequestBody ModeoperationForm modeoperationForm) {
        if (modeoperationForm != null) {
            try {
                    
modeoperationForm.setActive("1"); 
modeoperationForm.setCreateddate(new Date()); 
modeoperationForm.setModifyddate(new Date()); 
 if (modeoperationForm.getModeoperationid() > 0) {
                  
modeoperationService.updateModeoperation(modeoperationForm);
                }else{
                    modeoperationService.addModeoperation(modeoperationForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Modeoperation  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return modeoperationForm;
    }
    
    @RequestMapping(value = "/editmodeoperation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ModeoperationForm editModeoperationdata(@RequestBody final ModeoperationForm modeoperationForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        ModeoperationForm modeoperationForm1 = modeoperationService.editModeoperation(modeoperationForm.getModeoperationid());
        return modeoperationForm1;
    }
    @RequestMapping(value = "/getmodeoperationlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getModeoperationList(@RequestBody final TableForm modeoperationForm) throws Exception {
        modeoperationService.getModeoperationList(modeoperationForm);
        return modeoperationForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletemodeoperation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteModeoperation(@RequestBody final String modeoperationForm) throws Exception {
       String str[]=modeoperationForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    modeoperationService.deleteModeoperation(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getModeoperationFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getModeoperationcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> modeoperationlist = modeoperationService.getModeoperationComboList( request);
        return modeoperationlist;
    }
}
 
