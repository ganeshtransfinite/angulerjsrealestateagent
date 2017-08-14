/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

/**
 *
 * @author user
 */
public class testdb1 {

    public static void main(String[] args) {
        //creating configuration object  
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  

        //creating seession factory object  
        SessionFactory sessionfactory = cfg.buildSessionFactory();
        String table = "Property";
   
        //creating session object  
        Session session = sessionfactory.openSession();

        HashMap<String, Object> hs = new HashMap();

        int type = 1;
        boolean status = false;

        List l = session.createQuery("select count(*) as cnt from User user where  "
                + " user.active='1'").list();
        hs.put("User", l);

        hs.put("Property Rent", session.createQuery("select count(*) as cnt from Property property where property.status=false "
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

        q = session.createQuery("select YEAR(property.nextavilabledate) as  year,"
                + "QUARTER(property.nextavilabledate) as  month  ,count( property)  as count from "
                + " Property property"
                + " where property.status=true "
                + "and property.propertytobeid.propertytobeid=1 and "
                + " property.active='1' group by YEAR(property.nextavilabledate) , QUARTER(property.nextavilabledate) order by year , month"
        );
        q.setResultTransformer(Transformers.aliasToBean(GraphData.class));
        l = q.list();
        hs.put("QUARTER Property Rent Available", l);

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
        
        session.close();

        System.out.println("successfully saved");
    }
}
