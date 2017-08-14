package pms.property;

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
 * @property user
 */
@Controller
public class PropertyController {

    @Autowired(required = true)
    PropertyService propertyService;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveProperty.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    PropertyForm addProperty(@RequestBody PropertyForm propertyForm) {
        if (propertyForm != null) {
            try {

                propertyForm.setActive("1");
                propertyForm.setCreateddate(new Date());
                propertyForm.setModifyddate(new Date());
                if (propertyForm.getPropertyid() > 0) {

                    propertyService.updateProperty(propertyForm);
                } else {
                    propertyService.addProperty(propertyForm);
                }

                //              model.addAttribute("msg", "Succesfully Property  Saved.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return propertyForm;
    }

    @RequestMapping(value = "/editproperty.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PropertyForm editPropertydata(@RequestBody final PropertyForm propertyForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        PropertyForm propertyForm1 = propertyService.editProperty(propertyForm.getPropertyid());
        return propertyForm1;
    }

    @RequestMapping(value = "/duplicate.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean duplicate(@RequestBody final String str) throws Exception {
        PropertyForm propertyForm = new ObjectMapper().readValue(str, PropertyForm.class);
        
        return propertyService.duplicate(propertyForm);
        
    }

    @RequestMapping(value = "/getpropertylist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getPropertyList(@RequestBody final TableForm propertyForm) throws Exception {
        propertyService.getPropertyList(propertyForm);
        return propertyForm;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteproperty.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteProperty(@RequestBody final String propertyForm) throws Exception {
        String str[] = propertyForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    propertyService.deleteProperty(Integer.parseInt(str[i]));
                }

                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/getPropertyFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getPropertycomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> propertylist = propertyService.getPropertyComboList(request);
        return propertylist;
    }
}
