package pms.propertytobe;

import pms.propertytobe.PropertytobeForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.propertytobe.PropertytobeForm;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import admin.filter.CommonController;
import admin.filter.CommonUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import admin.user.UserForm;

/**
 *
 * @propertytobe Vision
 */
@Repository
public class PropertytobeDAOImpl implements PropertytobeDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addPropertytobe(PropertytobeForm propertytobeForm) {
        setFK(propertytobeForm);
        sessionfactory.getCurrentSession().save(propertytobeForm);
    }

    @Override
    public TableForm getPropertytobeList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertytobeForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("propertytobeid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(propertytobeName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<PropertytobeForm> list = (List<PropertytobeForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public PropertytobeForm editPropertytobe(Integer id) {
        PropertytobeForm propertytobeForm = (PropertytobeForm) sessionfactory.getCurrentSession().get(PropertytobeForm.class, id);

        if (propertytobeForm.getUserid() != null) {
            propertytobeForm.setSelectuserid(propertytobeForm.getUserid().getUserid());
        }
        return propertytobeForm;
    }

    @Override
    public void updatePropertytobe(PropertytobeForm propertytobeForm) {
        setFK(propertytobeForm);
        sessionfactory.getCurrentSession().update(propertytobeForm);
    }

    @Override
    public void deletePropertytobe(Integer id) {
        PropertytobeForm propertytobeForm = (PropertytobeForm) sessionfactory.getCurrentSession().load(PropertytobeForm.class, id);

        if (propertytobeForm != null) {
            propertytobeForm.setActive("0");
            sessionfactory.getCurrentSession().update(propertytobeForm);
        }

    }

    @Override
    public List<SelectCombo> getPropertytobeComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertytobe." + request.getParameter("col") + " , count(*) as cnt from Propertytobe propertytobe "
                    + "where propertytobe.active='1' "
                    + "group by propertytobe." + request.getParameter("col") + " order by propertytobe." + request.getParameter("col") + " ").list();
            //  List list = (List) c.addOrder(Order.asc(col)).list();
            //String data = "";
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(0,
                        (String) l.get(i)[0],
                        ((Long) l.get(i)[1]).intValue()));
                //data += propertyForm + ",";
                //      al.add(new SelectCombo(propertyForm.getPropertyid(), propertyForm.getFlatno()));
            }
        } else if (request.getParameter("table") != null) {
            String table = request.getParameter("table").toString().trim();
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertytobe.propertytobeid,propertytobe.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".propertytobeid  propertytobe where propertytobe.propertytobeid = " + table.toLowerCase() + ".propertytobeid.propertytobeid and  propertytobe.active='1' group by propertytobe.propertytobeid,propertytobe.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertytobe.propertytobeid,propertytobe.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".propertytobeid  propertytobe where  propertytobe.active='1' group by propertytobe.propertytobeid,propertytobe.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertytobeForm.class);
//
//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<PropertytobeForm> list = (List<PropertytobeForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (PropertytobeForm propertytobeForm : list) {
                SelectCombo obj = (new SelectCombo(propertytobeForm.getPropertytobeid(), propertytobeForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(propertytobeForm.getPropertytobeid(), propertytobeForm.getName()));
            }
        }
        return al;
    }

    public void setFK(PropertytobeForm propertytobeForm) {

        if (propertytobeForm.getSelectuserid() > 0) {
            propertytobeForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, propertytobeForm.getSelectuserid()));
        }
    }
}
