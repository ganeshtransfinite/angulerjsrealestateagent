package admin.role;

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
 * @role user
 */
@Controller
public class RoleController {

    @Autowired(required = true)
    RoleService roleService;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveRole.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    RoleForm addRole(@RequestBody RoleForm roleForm) {
        if (roleForm != null) {
            try {

                roleForm.setActive("1");
                roleForm.setCreateddate(new Date());
                roleForm.setModifyddate(new Date());
                if (roleForm.getRoleid() > 0) {

                    roleService.updateRole(roleForm);
                } else {
                    roleService.addRole(roleForm);
                }

                //              model.addAttribute("msg", "Succesfully Role  Saved.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return roleForm;
    }

    @RequestMapping(value = "/editrole.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    RoleForm editRoledata(@RequestBody final RoleForm roleForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        RoleForm roleForm1 = roleService.editRole(roleForm.getRoleid());
        return roleForm1;
    }

    @RequestMapping(value = "/getrolelist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getRoleList(@RequestBody final TableForm roleForm) throws Exception {
        roleService.getRoleList(roleForm);
        return roleForm;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleterole.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteRole(@RequestBody final String roleForm) throws Exception {
        String str[] = roleForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    roleService.deleteRole(Integer.parseInt(str[i]));
                }

                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/getRoleFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getRolecomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> rolelist = roleService.getRoleComboList(request);
        return rolelist;
    }
    
     @RequestMapping(value = "/getRoleDefaultlist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getRoleDefaultlist(HttpServletRequest request) throws Exception {

        roleService.defultSetting();//etRoleComboList(request);
        
        return "ok";
    }
}
