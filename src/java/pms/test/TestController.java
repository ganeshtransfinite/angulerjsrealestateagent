package pms.test;
 
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
 * @test user
 */
@Controller
public class TestController {
     @Autowired(required = true)
    TestService testService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveTest.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    TestForm addTest(@RequestBody TestForm testForm) {
        if (testForm != null) {
            try {
                    
testForm.setActive("1"); 
testForm.setCreateddate(new Date()); 
testForm.setModifyddate(new Date()); 
 if (testForm.getTestid() > 0) {
                  
testService.updateTest(testForm);
                }else{
                    testService.addTest(testForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Test  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return testForm;
    }
    
    @RequestMapping(value = "/edittest.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TestForm editTestdata(@RequestBody final TestForm testForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        TestForm testForm1 = testService.editTest(testForm.getTestid());
        return testForm1;
    }
    @RequestMapping(value = "/gettestlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getTestList(@RequestBody final TableForm testForm) throws Exception {
        testService.getTestList(testForm);
        return testForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletetest.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteTest(@RequestBody final String testForm) throws Exception {
       String str[]=testForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    testService.deleteTest(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getTestFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getTestcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> testlist = testService.getTestComboList( request);
        return testlist;
    }
}
 
