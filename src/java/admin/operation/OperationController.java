package admin.operation;

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
 * @operation user
 */
@Controller
public class OperationController {

    @Autowired(required = true)
    OperationService operationService;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveOperation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    OperationForm addOperation(@RequestBody OperationForm operationForm) {
        if (operationForm != null) {
            try {

                operationForm.setActive("1");
                operationForm.setCreateddate(new Date());
                operationForm.setModifyddate(new Date());
                if (operationForm.getOperationid() > 0) {

                    operationService.updateOperation(operationForm);
                } else {
                    operationService.addOperation(operationForm);
                }

                //              model.addAttribute("msg", "Succesfully Operation  Saved.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return operationForm;
    }

    @RequestMapping(value = "/editoperation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    OperationForm editOperationdata(@RequestBody final OperationForm operationForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        OperationForm operationForm1 = operationService.editOperation(operationForm.getOperationid());
        return operationForm1;
    }

    @RequestMapping(value = "/getoperationlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getOperationList(@RequestBody final TableForm operationForm) throws Exception {
        operationService.getOperationList(operationForm);
        return operationForm;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteoperation.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteOperation(@RequestBody final String operationForm) throws Exception {
        String str[] = operationForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    operationService.deleteOperation(Integer.parseInt(str[i]));
                }

                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/getOperationFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getOperationcomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> operationlist = operationService.getOperationComboList(request);
        return operationlist;
    }
}
