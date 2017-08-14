/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import admin.backup.FilRestore;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import pms.property.PropertyDateForm;
import pms.property.PropertyForm;

/**
 *
 * @author user
 */
public class BackRestoreDB {

    public static void data(Session session, int id) {
        Criteria cr = session.createCriteria(PropertyForm.class);
        //   cr = cr.add(Restrictions.eq("propertytobeid.propertytobeid", id));
        cr = cr.add(Restrictions.like("active", "1"));
        cr = cr.add(Restrictions.eq("status", true));
        List<PropertyForm> l = cr.list();
        int k = 0;
        Collections.shuffle(l);
        int mod = l.size() / 600;
        for (int i = 0; i < l.size(); i++) {
            Transaction tx = session.beginTransaction();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, -k);
            System.out.println(c.getTime());
            if (i % mod == 0) {
                k++;
            }
            l.get(i).setClosedate(c.getTime());

            //     c = Calendar.getInstance();
            c.add(Calendar.MONTH, -11);
            System.out.println(c.getTime());
            l.get(i).setAvailablefrom(c.getTime());

            c.add(Calendar.MONTH, 22);
            System.out.println(c.getTime());
            l.get(i).setNextavilabledate(c.getTime());
            System.out.println();

            session.update(l.get(i));
            tx.commit();
            System.out.println("Data : " + k);
        }
    }

    public static void main(String[] args) {
        //creating configuration object  
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  

        //creating seession factory object  
        SessionFactory sessionfactory = cfg.buildSessionFactory();
        String table = "Property";
        boolean status = false;
        int type = 1;

        //creating session object  
        Session session = sessionfactory.openSession();

//        new FilRestore().loadData(session, "G:\\phd\\PMS\\PMS\\db\\Rental XL- 1 March to Till Date1.xls");
//        new FilRestore().loadData(session, "G:\\phd\\PMS\\PMS\\db\\Sales XL  14 March 2016.xls");
        data(session, 1);
        // data(session, 2);

        session.close();
        System.out.println("successfully saved");
    }
}
