package pms.budgetsale;
 
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
 * @budgetsale user
 */
@Controller
public class BudgetsaleController {
     @Autowired(required = true)
    BudgetsaleService budgetsaleService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBudgetsale.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    BudgetsaleForm addBudgetsale(@RequestBody BudgetsaleForm budgetsaleForm) {
        if (budgetsaleForm != null) {
            try {
                    
budgetsaleForm.setActive("1"); 
budgetsaleForm.setCreateddate(new Date()); 
budgetsaleForm.setModifyddate(new Date()); 
 if (budgetsaleForm.getBudgetsaleid() > 0) {
                  
budgetsaleService.updateBudgetsale(budgetsaleForm);
                }else{
                    budgetsaleService.addBudgetsale(budgetsaleForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Budgetsale  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return budgetsaleForm;
    }
    
    @RequestMapping(value = "/editbudgetsale.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    BudgetsaleForm editBudgetsaledata(@RequestBody final BudgetsaleForm budgetsaleForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        BudgetsaleForm budgetsaleForm1 = budgetsaleService.editBudgetsale(budgetsaleForm.getBudgetsaleid());
        return budgetsaleForm1;
    }
    @RequestMapping(value = "/getbudgetsalelist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getBudgetsaleList(@RequestBody final TableForm budgetsaleForm) throws Exception {
        budgetsaleService.getBudgetsaleList(budgetsaleForm);
        return budgetsaleForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebudgetsale.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteBudgetsale(@RequestBody final String budgetsaleForm) throws Exception {
       String str[]=budgetsaleForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    budgetsaleService.deleteBudgetsale(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getBudgetsaleFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getBudgetsalecomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> budgetsalelist = budgetsaleService.getBudgetsaleComboList( request);
        return budgetsalelist;
    }
}
 
