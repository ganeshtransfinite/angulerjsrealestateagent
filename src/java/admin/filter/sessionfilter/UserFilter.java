package admin.filter.sessionfilter;

import admin.rolepermission.RolepermissionService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component(value="userfilter")
@Controller
public class UserFilter implements Filter {

    @Autowired(required = true)
    RolepermissionService rolepermissionService;

    @Autowired(required = true)
    private SessionFactory sessionfactory;

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                    // throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader(
                "Access-Control-Max-Age", "3600");
        response.setHeader(
                "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                                   chain.doFilter(req, res);
//       
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // super.init(filterConfig);
//        ServletContext servletContext = config.getServletContext();
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//
//        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
//
//        Object obj= autowireCapableBeanFactory.getBean("userfilter");
//        System.out.println("OBJ:" +obj);
        //sirhWSConsumer = (ISirhWSConsumer) autowireCapableBeanFactory.getBean("sirhWSConsumer");

//        ServletContext servletContext = filterConfig.getServletContext();
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//
//        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
//
//        webApplicationContext.getAutowireCapableBeanFactory();
//        autowireCapableBeanFactory.autowireBean(this);

		//RolepermissionServiceImpl obj=autowireCapableBeanFactory.createBean(RolepermissionServiceImpl.class);
                //System.out.println(obj.editRolepermission(71));
        //Object obj=autowireCapableBeanFactory.configureBean(this,"sessionFactory");
    }

    public void destroy() {
    }

}
