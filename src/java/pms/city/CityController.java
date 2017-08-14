package pms.city;
 
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
 * @city user
 */
@Controller
public class CityController {
     @Autowired(required = true)
    CityService cityService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCity.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    CityForm addCity(@RequestBody CityForm cityForm) {
        if (cityForm != null) {
            try {
                    
cityForm.setActive("1"); 
cityForm.setCreateddate(new Date()); 
cityForm.setModifyddate(new Date()); 
 if (cityForm.getCityid() > 0) {
                  
cityService.updateCity(cityForm);
                }else{
                    cityService.addCity(cityForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully City  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return cityForm;
    }
    
    @RequestMapping(value = "/editcity.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    CityForm editCitydata(@RequestBody final CityForm cityForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CityForm cityForm1 = cityService.editCity(cityForm.getCityid());
        return cityForm1;
    }
    @RequestMapping(value = "/getcitylist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getCityList(@RequestBody final TableForm cityForm) throws Exception {
        cityService.getCityList(cityForm);
        return cityForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletecity.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteCity(@RequestBody final String cityForm) throws Exception {
       String str[]=cityForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    cityService.deleteCity(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getCityFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getCitycomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> citylist = cityService.getCityComboList( request);
        return citylist;
    }
}
 
