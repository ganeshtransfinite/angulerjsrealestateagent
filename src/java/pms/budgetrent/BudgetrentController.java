package pms.budgetrent;
 
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
 * @budgetrent user
 */
@Controller
public class BudgetrentController {
     @Autowired(required = true)
    BudgetrentService budgetrentService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveBudgetrent.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    BudgetrentForm addBudgetrent(@RequestBody BudgetrentForm budgetrentForm) {
        if (budgetrentForm != null) {
            try {
                    
budgetrentForm.setActive("1"); 
budgetrentForm.setCreateddate(new Date()); 
budgetrentForm.setModifyddate(new Date()); 
 if (budgetrentForm.getBudgetrentid() > 0) {
                  
budgetrentService.updateBudgetrent(budgetrentForm);
                }else{
                    budgetrentService.addBudgetrent(budgetrentForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Budgetrent  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return budgetrentForm;
    }
    
    @RequestMapping(value = "/editbudgetrent.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    BudgetrentForm editBudgetrentdata(@RequestBody final BudgetrentForm budgetrentForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        BudgetrentForm budgetrentForm1 = budgetrentService.editBudgetrent(budgetrentForm.getBudgetrentid());
        return budgetrentForm1;
    }
    @RequestMapping(value = "/getbudgetrentlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getBudgetrentList(@RequestBody final TableForm budgetrentForm) throws Exception {
        budgetrentService.getBudgetrentList(budgetrentForm);
        return budgetrentForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletebudgetrent.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteBudgetrent(@RequestBody final String budgetrentForm) throws Exception {
       String str[]=budgetrentForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    budgetrentService.deleteBudgetrent(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getBudgetrentFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getBudgetrentcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> budgetrentlist = budgetrentService.getBudgetrentComboList( request);
        return budgetrentlist;
    }
}
 
