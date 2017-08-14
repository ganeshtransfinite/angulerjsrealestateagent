package pms.customertype;

import pms.customertype.CustomertypeForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.customertype.CustomertypeForm;
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
 * @customertype Vision
 */
@Repository
public class CustomertypeDAOImpl implements CustomertypeDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addCustomertype(CustomertypeForm customertypeForm) {
        setFK(customertypeForm);
        sessionfactory.getCurrentSession().save(customertypeForm);
    }

    @Override
    public TableForm getCustomertypeList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(CustomertypeForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("customertypeid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(customertypeName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<CustomertypeForm> list = (List<CustomertypeForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public CustomertypeForm editCustomertype(Integer id) {
        CustomertypeForm customertypeForm = (CustomertypeForm) sessionfactory.getCurrentSession().get(CustomertypeForm.class, id);

        if (customertypeForm.getUserid() != null) {
            customertypeForm.setSelectuserid(customertypeForm.getUserid().getUserid());
        }
        return customertypeForm;
    }

    @Override
    public void updateCustomertype(CustomertypeForm customertypeForm) {
        setFK(customertypeForm);
        sessionfactory.getCurrentSession().update(customertypeForm);
    }

    @Override
    public void deleteCustomertype(Integer id) {
        CustomertypeForm customertypeForm = (CustomertypeForm) sessionfactory.getCurrentSession().load(CustomertypeForm.class, id);

        if (customertypeForm != null) {
            customertypeForm.setActive("0");
            sessionfactory.getCurrentSession().update(customertypeForm);
        }

    }

    @Override
    public List<SelectCombo> getCustomertypeComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select customertype." + request.getParameter("col") + " , count(*) as cnt from Customertype customertype "
                    + "where customertype.active='1' "
                    + "group by customertype." + request.getParameter("col") + " order by customertype." + request.getParameter("col") + " ").list();
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
            int type = 1;
            boolean status = false;
            if (request.getParameter("type").equals("1")) {
                status = false;
                type = 1;
            } else if (request.getParameter("type").equals("2")) {
                status = false;
                type = 2;
            } else if (request.getParameter("type").equals("3")) {
                status = true;
                type = 1;
            } else if (request.getParameter("type").equals("4")) {
                status = true;
                type = 2;
            }
            String cond = request.getParameter("table").equals("Property") ? " and " + table.toLowerCase() + ".status=" + status + " and " + table.toLowerCase() + ".propertytobeid.propertytobeid=" + type : " ";
          
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select customertype.customertypeid,customertype.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".customertypeid  customertype where customertype.customertypeid = " + table.toLowerCase() + ".customertypeid.customertypeid and  customertype.active='1' group by customertype.customertypeid,customertype.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select customertype.customertypeid,customertype.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".customertypeid  customertype where  customertype.active='1' "
                    +cond+ " and " + table.toLowerCase() + ".active='1' "
                    + " group by customertype.customertypeid,customertype.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(CustomertypeForm.class);
//
//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<CustomertypeForm> list = (List<CustomertypeForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (CustomertypeForm customertypeForm : list) {
                SelectCombo obj = (new SelectCombo(customertypeForm.getCustomertypeid(), customertypeForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(customertypeForm.getCustomertypeid(), customertypeForm.getName()));
            }
        }
        return al;
    }

    public void setFK(CustomertypeForm customertypeForm) {

        if (customertypeForm.getSelectuserid() > 0) {
            customertypeForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, customertypeForm.getSelectuserid()));
        }
    }
}
