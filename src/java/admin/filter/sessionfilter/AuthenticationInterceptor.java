/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter.sessionfilter;

import admin.filter.UserSession;
import admin.rolepermission.RolepermissionService;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired(required = true)
    RolepermissionService rolepermissionService;

//    @Override
//    public boolean preHandle(HttpServletRequest request,
//            HttpServletResponse response, Object handler) throws Exception {
//    //    logger.info("Before handling the request");
//        System.out.println("handlet");
//        return true;
//    }
    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        try {
            String url = hsr.getRequestURI().toString();
            if (url.indexOf("/") != -1 && url.indexOf(".json") != -1) {
                url = url.substring(url.lastIndexOf("/"), url.indexOf(".json") + 5);
            }
//            System.out.println(hsr.getParameter("userid") + ":" + hsr.getParameter("JSESSIONID")
//                    + "role :" + hsr.getParameter("role") + "url:" + hsr.getRequestURI());
            if ((url.indexOf("/web/") != -1 && url.indexOf(".json") == -1) || url.endsWith("/en.json") || url.equals("/WebPMS") || url.equals("/login.json") || url.equals("/getRoleDefaultlist.json")) {
                return true;
            } else if (hsr.getParameter("JSESSIONID") != null && hsr.getSession() != null
                    && hsr.getSession().getServletContext().getAttribute(hsr.getParameter("JSESSIONID")) != null) {
                UserSession user = (UserSession) hsr.getSession().getServletContext().getAttribute(hsr.getParameter("JSESSIONID"));
                if (user != null && user.userid == Integer.parseInt(hsr.getParameter("userid"))) {
                    long time = new Date().getTime() - user.getTime().getTime();
                    time = time / (1000 * 60 * 60);

                    if (time < 60) {
                        HashMap<String, Boolean> roleurl = (HashMap<String, Boolean>) hsr.getSession().getServletContext().getAttribute("role-" + user.getRolename());
                        if (url.endsWith("/getRolepermissionFormColUpdate.json") || url.endsWith("/propertyEditStatus.json") || url.endsWith("/getPropertyDistinct.json")
                                || url.endsWith("/uploaddata") || url.indexOf("/duplicate.json") != -1 || url.indexOf("/checkExistCustomer.json") != -1
                                || url.indexOf("/getAptFormcombolist.json") != -1 || url.indexOf("/checkExistUser.json") != -1
                                || url.indexOf("/dashgetproperty.json") != -1) {//
                            return true;
                        } else if (roleurl.get(url)) {
                            return true;
                        }

                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        hsr1.getWriter().write("LOGIN ERROR");

        return false;
    }

}
