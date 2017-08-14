package admin.datarecovery;

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
 * @datarecovery user
 */
@Controller
public class DatarecoveryController {

    @Autowired(required = true)
    DatarecoveryService datarecoveryService;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveDatarecovery.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    DatarecoveryForm addDatarecovery(@RequestBody DatarecoveryForm datarecoveryForm) {
        if (datarecoveryForm != null) {
            try {

                datarecoveryForm.setActive("1");
                datarecoveryForm.setCreateddate(new Date());
                datarecoveryForm.setModifyddate(new Date());
                if (datarecoveryForm.getDatarecoveryid() > 0) {

                    datarecoveryService.updateDatarecovery(datarecoveryForm);
                } else {
                    datarecoveryService.addDatarecovery(datarecoveryForm);
                }

                //              model.addAttribute("msg", "Succesfully Datarecovery  Saved.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return datarecoveryForm;
    }

    @RequestMapping(value = "/editdatarecovery.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    DatarecoveryForm editDatarecoverydata(@RequestBody final DatarecoveryForm datarecoveryForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        DatarecoveryForm datarecoveryForm1 = datarecoveryService.editDatarecovery(datarecoveryForm.getDatarecoveryid());
        return datarecoveryForm1;
    }

    @RequestMapping(value = "/getdatarecoverylist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getDatarecoveryList(@RequestBody final TableForm datarecoveryForm) throws Exception {
        datarecoveryService.getDatarecoveryList(datarecoveryForm);
        return datarecoveryForm;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletedatarecovery.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteDatarecovery(@RequestBody final String datarecoveryForm) throws Exception {
        String str[] = datarecoveryForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    datarecoveryService.deleteDatarecovery(Integer.parseInt(str[i]));
                }

                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/getDatarecoveryFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getDatarecoverycomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> datarecoverylist = datarecoveryService.getDatarecoveryComboList(request);
        return datarecoverylist;
    }
}
