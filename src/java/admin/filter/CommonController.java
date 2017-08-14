/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import admin.backup.FilRestore;
import admin.backup.Hibernate3ToXstreamXmlDatabaseBackuper;
import admin.rolepermission.RolepermissionForm;
import admin.file.FileForm;
import admin.file.FileService;
import admin.user.UserForm;
import admin.user.UserService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import pms.property.PropertyService;

@Controller
public class CommonController {

    @Autowired()
    private SessionFactory sessionfactory;

    @Autowired(required = true)
    UserService userService;

    @Autowired(required = true)
    FileService fileService;

    @Autowired(required = true)
    PropertyService propertyService;

    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, @PathVariable String value) {
        try {

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    String redirectUrl = "/web/index.html";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String processForm() {
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String processForm1() {
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/login.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    @Transactional
    UserSession login(@RequestBody final UserForm userForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        List<UserForm> userForm1 = (List<UserForm>) sessionfactory.getCurrentSession().createCriteria(UserForm.class).
                add(
                        Expression.eq("email", userForm.getEmail())).add(Expression.eq("password", userForm.getPassword()))
                .addOrder(Order.desc("userid")).list();
        //   List<UserForm> userForm1 = userService.login(userForm);
        if (userForm1.size() > 0) {
            List<RolepermissionForm> roleForm1 = (List<RolepermissionForm>) sessionfactory.getCurrentSession().
                    createCriteria(RolepermissionForm.class).add(Restrictions.eq("roleid.roleid", userForm1.get(0).getRoleid().getRoleid())).list();
            UserSession ui = new UserSession(request.getSession().getId(),
                    userForm1.get(0).getUserid(), userForm1.get(0).getEmail(), userForm1.get(0).getRoleid().getName());
            ui.setUser(new UserForm(userForm1.get(0)));
            request.getSession().getServletContext().setAttribute(request.getSession().getId(),
                    ui);

            HashMap<String, Boolean> roleurl = new HashMap();

            for (int i = 0; i < roleForm1.size(); i++) {
                if (ui.role.get(roleForm1.get(i).getOperationid().getName()) == null) {
                    ui.role.put(roleForm1.get(i).getOperationid().getName(), new HashMap<String, Boolean>());
                    //  roleurl.put(roleForm1.get(i).getOperationid().getName(), new HashMap<String, String>());
                }
                ui.role.get(roleForm1.get(i).getOperationid().getName()).
                        put(roleForm1.get(i).getOperationid().getFunname(), new Boolean(roleForm1.get(i).isPfunction()));
                roleurl.put(roleForm1.get(i).getOperationid().getUrl(), roleForm1.get(i).isPfunction());

                //ui.role.put(roleForm1.get(i).getOperationid().getName(), roleForm1.get(i));
            }
            if (request.getSession().getServletContext().getAttribute("role-" + userForm1.get(0).getRoleid().getName()) == null) {
                request.getSession().getServletContext().setAttribute("role-" + userForm1.get(0).getRoleid().getName(), roleurl);
            }

            return ui;
        }

        return new UserSession(
                request.getSession().getId(), 0);

    }

    @RequestMapping(value = "/uploaddata", method = RequestMethod.POST)
    public @ResponseBody
    @Transactional
    int upload(MultipartHttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("type").equals("uploadexcel")) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("file");
            System.out.println(request.getParameter("fileid"));
            try {
                String file = request.getSession().getServletContext().getRealPath("/") +"file/"+ multipartFile.getOriginalFilename().replaceAll(" ", "_");
                FileOutputStream f = new FileOutputStream(new File(file));
                f.write(multipartFile.getBytes());
                f.close();
                int i = FilRestore.loadDataUpload(sessionfactory.getCurrentSession(), new File(file).getAbsolutePath());
                new File(file).delete();
                return i;
            } catch (Exception ex) {
                Logger.getLogger(CommonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        } else {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("file");
            System.out.println(request.getParameter("fileid"));
            FileForm file = new FileForm();

            if (!request.getParameter("fileid").equals("0")) {
                file = fileService.editFile(Integer.parseInt(request.getParameter("fileid")));
            } else {
                file.setCreateddate(new Date());
            }

            file.setModifyddate(new Date());
            file.setFilename(multipartFile.getOriginalFilename());
            file.setType(request.getParameter("type"));
            file.setConenttype(multipartFile.getContentType());
            try {
                file.setContent(multipartFile.getBytes());
                file.setContenticon(fileService.getIcon(multipartFile.getBytes()));
            } catch (IOException ex) {
                Logger.getLogger(CommonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            file.setUserid(userService.editUser(Integer.parseInt(request.getParameter("userid"))));

            file.setActive("1");

            file.setCreateddate(new Date());
            if (file.getFileid() > 0) {

                fileService.updateFile(file);
            } else {
                fileService.addFile(file);
            }
            return file.getFileid();
        }

    }

    @RequestMapping(value = "/backup.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    @Transactional
    String backup() throws Exception {
        try {

            Map<String, ClassMetadata> map = (Map<String, ClassMetadata>) sessionfactory.getAllClassMetadata();
            Class[] cl = new Class[map.size()];
            int i = 0;
            for (String entityName : map.keySet()) {
                cl[i] = Class.forName(entityName);
                i++;

            }

            Hibernate3ToXstreamXmlDatabaseBackuper st = new Hibernate3ToXstreamXmlDatabaseBackuper();
            st.setDatabaseBackupLocation(new FileSystemResource("db.xml"));
            st.setHibernateTemplate(new HibernateTemplate(sessionfactory));
            st.setDatabaseClasses(cl);
            st.dumpDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

}
