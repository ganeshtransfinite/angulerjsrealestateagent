package admin.user;

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
 * @user user
 */
@Controller
public class UserController {

    @Autowired(required = true)
    UserService userService;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveUser.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    UserForm addUser(@RequestBody UserForm userForm) {
        if (userForm != null) {
            try {

                userForm.setActive("1");
                userForm.setCreateddate(new Date());
                userForm.setModifyddate(new Date());
                if (userForm.getUserid() > 0) {

                userForm.setOption( userService.updateUser(userForm));
                } else {
                    userService.addUser(userForm);
                }

                //              model.addAttribute("msg", "Succesfully User  Saved.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return userForm;
    }

    @RequestMapping(value = "/edituser.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    UserForm editUserdata(@RequestBody final UserForm userForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        UserForm userForm1 = userService.editUser(userForm.getUserid());
        return userForm1;
    }

    @RequestMapping(value = "/getuserlist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getUserList(@RequestBody final TableForm userForm) throws Exception {
        userService.getUserList(userForm);
        return userForm;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteuser.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteUser(@RequestBody final String userForm) throws Exception {
        String str[] = userForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    userService.deleteUser(Integer.parseInt(str[i]));
                }

                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/getUserFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getUsercomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> userlist = userService.getUserComboList(request);
        return userlist;
    }
    
     @RequestMapping(value = "/checkExistUser.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    boolean checkExistCustomer(HttpServletRequest request) throws Exception {

        return userService.checkExist(request.getParameter("col"), request.getParameter("val"), Integer.parseInt(request.getParameter("id")));

    }
}
