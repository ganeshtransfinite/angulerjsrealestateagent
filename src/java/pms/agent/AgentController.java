package pms.agent;
 
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
 * @agent user
 */
@Controller
public class AgentController {
     @Autowired(required = true)
    AgentService agentService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveAgent.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    AgentForm addAgent(@RequestBody AgentForm agentForm) {
        if (agentForm != null) {
            try {
                    
agentForm.setActive("1"); 
agentForm.setCreateddate(new Date()); 
agentForm.setModifyddate(new Date()); 
 if (agentForm.getAgentid() > 0) {
                  
agentService.updateAgent(agentForm);
                }else{
                    agentService.addAgent(agentForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Agent  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return agentForm;
    }
    
    @RequestMapping(value = "/editagent.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    AgentForm editAgentdata(@RequestBody final AgentForm agentForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        AgentForm agentForm1 = agentService.editAgent(agentForm.getAgentid());
        return agentForm1;
    }
    @RequestMapping(value = "/getagentlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getAgentList(@RequestBody final TableForm agentForm) throws Exception {
        agentService.getAgentList(agentForm);
        return agentForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteagent.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteAgent(@RequestBody final String agentForm) throws Exception {
       String str[]=agentForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    agentService.deleteAgent(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getAgentFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getAgentcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> agentlist = agentService.getAgentComboList( request);
        return agentlist;
    }
}
 
