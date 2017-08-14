package pms.propertyfacing;

import pms.propertyfacing.PropertyfacingForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.propertyfacing.PropertyfacingForm;
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
 * @propertyfacing Vision
 */
@Repository
public class PropertyfacingDAOImpl implements PropertyfacingDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addPropertyfacing(PropertyfacingForm propertyfacingForm) {
        setFK(propertyfacingForm);
        sessionfactory.getCurrentSession().save(propertyfacingForm);
    }

    @Override
    public TableForm getPropertyfacingList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertyfacingForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("propertyfacingid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(propertyfacingName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<PropertyfacingForm> list = (List<PropertyfacingForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public PropertyfacingForm editPropertyfacing(Integer id) {
        PropertyfacingForm propertyfacingForm = (PropertyfacingForm) sessionfactory.getCurrentSession().get(PropertyfacingForm.class, id);

        if (propertyfacingForm.getUserid() != null) {
            propertyfacingForm.setSelectuserid(propertyfacingForm.getUserid().getUserid());
        }
        return propertyfacingForm;
    }

    @Override
    public void updatePropertyfacing(PropertyfacingForm propertyfacingForm) {
        setFK(propertyfacingForm);
        sessionfactory.getCurrentSession().update(propertyfacingForm);
    }

    @Override
    public void deletePropertyfacing(Integer id) {
        PropertyfacingForm propertyfacingForm = (PropertyfacingForm) sessionfactory.getCurrentSession().load(PropertyfacingForm.class, id);

        if (propertyfacingForm != null) {
            propertyfacingForm.setActive("0");
            sessionfactory.getCurrentSession().update(propertyfacingForm);
        }

    } 

    @Override
    public List<SelectCombo> getPropertyfacingComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertyfacing." + request.getParameter("col") + " , count(*) as cnt from Propertyfacing propertyfacing "
                    + "where propertyfacing.active='1' "
                    + "group by propertyfacing." + request.getParameter("col") + " order by propertyfacing." + request.getParameter("col") + " ").list();
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
              boolean status = false;
            int type = 1;

            if (request.getParameter("type").equals("1")) {
                status = false;
                type = 1;
            } else if (request.getParameter("type").equals("2")) {
                status = false;
                type = 2;
            }else if (request.getParameter("type").equals("3")) {
                status = true;
                type = 1;
            } else if (request.getParameter("type").equals("4")) {
                status = true;
                type = 2;
            }
            
            String table = request.getParameter("table").toString().trim();
             String cond = request.getParameter("table").equals("Property") ? " and " + table.toLowerCase() + ".status=" + status + " and " + table.toLowerCase() + ".propertytobeid.propertytobeid=" + type : " ";
          
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertyfacing.propertyfacingid,propertyfacing.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".propertyfacingid  propertyfacing where propertyfacing.propertyfacingid = " + table.toLowerCase() + ".propertyfacingid.propertyfacingid and  propertyfacing.active='1' group by propertyfacing.propertyfacingid,propertyfacing.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertyfacing.propertyfacingid,propertyfacing.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".propertyfacingid  propertyfacing where  propertyfacing.active='1' "
                + cond +" and "+table.toLowerCase() +".active='1' "
                    + " group by propertyfacing.propertyfacingid,propertyfacing.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertyfacingForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<PropertyfacingForm> list = (List<PropertyfacingForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (PropertyfacingForm propertyfacingForm : list) {
                SelectCombo obj = (new SelectCombo(propertyfacingForm.getPropertyfacingid(), propertyfacingForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(propertyfacingForm.getPropertyfacingid(), propertyfacingForm.getName()));
            }
        }
        return al;
    }

    public void setFK(PropertyfacingForm propertyfacingForm) {

        if (propertyfacingForm.getSelectuserid() > 0) {
            propertyfacingForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, propertyfacingForm.getSelectuserid()));
        }
    }
}
