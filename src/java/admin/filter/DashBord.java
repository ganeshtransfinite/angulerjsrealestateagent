/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import pms.property.PropertyService;

/**
 *
 * @author user
 */
@Controller
public class DashBord {

    @Autowired()
    private SessionFactory sessionfactory;
    @Autowired(required = true)
    PropertyService propertyService;

    @RequestMapping(value = "/dashgetproperty.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    @Transactional
    HashMap<String, Object> getDashgetproperty(HttpServletRequest request) throws Exception {

        SimpleDateFormat sd = new SimpleDateFormat("MMM d, yyyy");
        String date = sd.format(new Date());
//            if (request.getSession().getServletContext().getAttribute(date) == null) {
//                request.getSession().getServletContext().setAttribute(date, "ok");
        propertyService.updateALLProperty();
//            }

        HashMap<String, Object> hs = new HashMap();
        Session session = sessionfactory.getCurrentSession();

        List l = session.createQuery("select count(*) as cnt from User user where  "
                + " user.active='1'").list();
        hs.put("User", l);

        hs.put("Property Rent Count", session.createQuery("select count(*) as cnt from Property property where property.status=false "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1'").list());

        hs.put("Property Rent Close", session.createQuery("select count(*) as cnt from Property property "
                + "where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1'").list());

        Query q = session.createQuery("select count(*) as cnt from"
                + " Property property where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' and nextavilabledate <= :now ");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 90);
        q.setDate("now", c.getTime());
        hs.put("Property Rent Available", q.list());

        hs.put("Property Sale", session.createQuery("select count(*) as cnt from "
                + "Property property where property.status=false "
                + "and property.propertytobeid.propertytobeid=2 and "
                + " property.active='1'").list());
        hs.put("Property Sale Close", session.createQuery("select count(*) as cnt from "
                + "Property property where property.status=true "
                + "and property.propertytobeid.propertytobeid=2 and "
                + " property.active='1'").list());

        hs.put("Inquiry", session.createQuery("select count(*) as cnt from Inquiry inquiry where  "
                + " inquiry.active='1'").list());

        hs.put("Customer", session.createQuery("select count(*) as cnt from Customer customer where  "
                + " customer.active='1'").list());
        hs.put("Agent", session.createQuery("select count(*) as cnt from Agent agent where  "
                + " agent.active='1'").list());

//        hs.put("Property Rent Close", (session.createQuery("select extract(year from property.closedate) as  year,"
//                + "extract(month from property.closedate) as  month, count(*) as cnt from "
//                + " Property property  "
//                + " where property.status=true "
//                + "and property.propertytobeid.propertytobeid=1 and "
//                + " property.active='1' group by year , month order by year , month "
        q = session.createQuery("select YEAR(property.closedate) as  year,"
                + "MONTH(property.closedate) as  month  ,count( property)  as count from "
                + " Property property  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.closedate) , MONTH(property.closedate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Monthly Property Rent Close", l);

        q = session.createQuery("select YEAR(property.closedate) as  year,"
                + "QUARTER(property.closedate) as  month  ,count( property)  as count from "
                + " Property property  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.closedate) , QUARTER(property.closedate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Quarter Property Rent Close", l);

        q = session.createQuery("select YEAR(property.closedate) as  year,"
                + " count( property)  as count from "
                + " Property property  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.closedate) order by year "
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Year Property Rent Close", l);

        q = session.createQuery("select YEAR(property.nextavilabledate) as  year,"
                + "MONTH(property.nextavilabledate) as  month  ,count( property)  as count from "
                + " Property property"
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.nextavilabledate) , MONTH(property.nextavilabledate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Monthly Property Rent Available", l);

        q = session.createQuery("select YEAR(property.availablefrom) as  year,"
                + "MONTH(property.availablefrom) as  month  ,count( property)  as count from "
                + " Property property"
                + " where property.status=false "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.availablefrom) , MONTH(property.availablefrom) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Property Rent", l);

        q = session.createQuery("select YEAR(property.nextavilabledate) as  year,"
                + "QUARTER(property.nextavilabledate) as  month  ,count( property)  as count from "
                + " Property property"
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.nextavilabledate) , QUARTER(property.nextavilabledate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Quarter Property Rent Available", l);

        q = session.createQuery("select YEAR(property.nextavilabledate) as  year,"
                + " count( property)  as count from "
                + " Property property"
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.nextavilabledate)  order by year "
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Year Property Rent Available", l);

        q = session.createQuery("select YEAR(property.closedate) as  year,"
                + "MONTH(property.closedate) as  month  ,count( property)  as count from "
                + " Property property  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=2 and "
                + " property.active='1' group by YEAR(property.closedate) , MONTH(property.closedate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Monthly Property Sale Close", l);

        q = session.createQuery("select YEAR(property.closedate) as  year,"
                + "QUARTER(property.closedate) as  month  ,count( property)  as count from "
                + " Property property  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=2 and "
                + " property.active='1' group by YEAR(property.closedate) , QUARTER(property.closedate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Quarter Property Sale Close", l);

        q = session.createQuery("select YEAR(property.closedate) as  year,"
                + " count( property)  as count from "
                + " Property property  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=2 and "
                + " property.active='1' group by YEAR(property.closedate) order by year "
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Year Property Sale Close", l);

        q = session.createQuery("select YEAR(property.closedate) as  year,"
                + "MONTH(property.closedate) as  month  ,count( property)  as count from "
                + " Property property  "
                + " where property.status=false "
                + "and property.propertytobeid.propertytobeid=2 and "
                + " property.active='1' group by YEAR(property.closedate) , MONTH(property.closedate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Monthly Property Sale", l);

        q = session.createQuery("select propertylocalityid.name as name ,count( property)  as count from "
                + " Property property join property.propertylocalityid propertylocalityid "
                + " where property.status=false "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by propertylocalityid.name    order by propertylocalityid.name "
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Location Property Rent", l);

        q = session.createQuery("select propertylocalityid.name as name ,count( property)  as count from "
                + " Property property join property.propertylocalityid propertylocalityid "
                + " where property.status=false "
                + "and property.propertytobeid.propertytobeid=2 and "
                + " property.active='1' group by propertylocalityid.name    order by propertylocalityid.name "
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Location Property Sale", l);

        q = session.createQuery("select propertylocalityid.name as name, "
                + " count( property)  as count from "
                + " Property property join property.propertylocalityid propertylocalityid  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and property.closedate > :now"
                + " and property.active='1' group by propertylocalityid.name order by propertylocalityid.name "
        );
        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -365);
        q.setDate("now", c.getTime());
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Location Property Rent Close", l);

        q = session.createQuery("select propertylocalityid.name as name, "
                + " count( property)  as count from "
                + " Property property join property.propertylocalityid propertylocalityid  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=2 and property.closedate > :now"
                + " and property.active='1' group by propertylocalityid.name order by propertylocalityid.name "
        );
        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -365);
        q.setDate("now", c.getTime());
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Location Property Sale Close", l);

        q = session.createQuery("select propertylocalityid.name as name, "
                + " count( property)  as count from "
                + " Property property join property.propertylocalityid propertylocalityid  "
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and   property.nextavilabledate < :now "
                + " and property.active='1' group by  propertylocalityid.name  order by  propertylocalityid.name "
        );
        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 90);
        q.setDate("now", c.getTime());
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Location Property Rent Available", l);

        q = session.createQuery("select propertylocalityid.name as name, "
                + " count( agent)  as count from "
                + " Agent agent join agent.propertylocalityid propertylocalityid  "
                + " where     "
                + "   agent.active='1' group by  propertylocalityid.name  order by  propertylocalityid.name "
        );

        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Location Agent", l);

//         q = session.createQuery("select propertylocalityid.name as name, "
//                + " count( custormer)  as count from "
//                + " Agent agent join agent.propertylocalityid propertylocalityid  "
//                + " where     "
//                + "   agent.active='1' group by  propertylocalityid.name  order by  propertylocalityid.name "
//        );
//
//        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
//        l = q.list();
//        hs.put("Location Customer", l);
//        q = session.createQuery("select propertylocalityid.name as name, "
//                + " count( customer)  as count from "
//                + " Customer customer join customer.propertylocalityid propertylocalityid  "
//                + " where     "
//                + "   customer.active='1' group by  propertylocalityid.name  order by  propertylocalityid.name "
//        );
//
//        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
//        l = q.list();
//        hs.put("Customer Agent", l);
        q = session.createQuery("select propertylocalityid.name as name, "
                + " count( inquiry)  as count from "
                + " Inquiry inquiry join inquiry.propertylocalityid propertylocalityid  "
                + " where   inquiry.dateofinquiry >= :now and "
                + "   inquiry.active='1' group by  propertylocalityid.name  order by  propertylocalityid.name "
        );
        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -365);
        q.setDate("now", c.getTime());
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("Inquiry Agent", l);
// customerService.getCustomerList(customerForm);
        return hs;
    }

}
