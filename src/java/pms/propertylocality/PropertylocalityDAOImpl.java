package pms.propertylocality;

import pms.propertylocality.PropertylocalityForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.propertylocality.PropertylocalityForm;
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

import pms.city.CityForm;
import admin.user.UserForm;

/**
 *
 * @propertylocality Vision
 */
@Repository
public class PropertylocalityDAOImpl implements PropertylocalityDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addPropertylocality(PropertylocalityForm propertylocalityForm) {
        setFK(propertylocalityForm);
        sessionfactory.getCurrentSession().save(propertylocalityForm);
    }

    @Override
    public TableForm getPropertylocalityList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertylocalityForm.class
        );

        cr = cr.createAlias("cityid", "cityid", Criteria.LEFT_JOIN);
        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("propertylocalityid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(propertylocalityName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<PropertylocalityForm> list = (List<PropertylocalityForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public PropertylocalityForm editPropertylocality(Integer id) {
        PropertylocalityForm propertylocalityForm = (PropertylocalityForm) sessionfactory.getCurrentSession().get(PropertylocalityForm.class, id);

        if (propertylocalityForm.getCityid() != null) {
            propertylocalityForm.setSelectcityid(propertylocalityForm.getCityid().getCityid());
        }
        if (propertylocalityForm.getUserid() != null) {
            propertylocalityForm.setSelectuserid(propertylocalityForm.getUserid().getUserid());
        }
        return propertylocalityForm;
    }

    @Override
    public void updatePropertylocality(PropertylocalityForm propertylocalityForm) {
        setFK(propertylocalityForm);
        sessionfactory.getCurrentSession().update(propertylocalityForm);
    }

    @Override
    public void deletePropertylocality(Integer id) {
        PropertylocalityForm propertylocalityForm = (PropertylocalityForm) sessionfactory.getCurrentSession().load(PropertylocalityForm.class, id);

        if (propertylocalityForm != null) {
            propertylocalityForm.setActive("0");
            sessionfactory.getCurrentSession().update(propertylocalityForm);
        }

    }

    @Override
    public List<SelectCombo> getPropertylocalityComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertylocality." + request.getParameter("col") + " , count(*) as cnt from Propertylocality propertylocality "
                    + "where propertylocality.active='1' "
                    + "group by propertylocality." + request.getParameter("col") + " order by propertylocality." + request.getParameter("col") + " ").list();
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
            }else if (request.getParameter("type").equals("3")) {
                status = true;
                type = 1;
            } else if (request.getParameter("type").equals("4")) {
                status = true;
                type = 2;
            } String cond = request.getParameter("table").equals("Property") ? " and " + table.toLowerCase() + ".status=" + status + " and " + table.toLowerCase() + ".propertytobeid.propertytobeid=" + type : " ";
          
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertylocality.propertylocalityid,propertylocality.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".propertylocalityid  propertylocality where propertylocality.propertylocalityid = " + table.toLowerCase() + ".propertylocalityid.propertylocalityid and  propertylocality.active='1' group by propertylocality.propertylocalityid,propertylocality.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select propertylocality.propertylocalityid,propertylocality.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".propertylocalityid  propertylocality where  propertylocality.active='1' "
                     +cond +" and "+table.toLowerCase() +".active='1' "
                    + " group by propertylocality.propertylocalityid,propertylocality.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertylocalityForm.class);

            if (request.getParameter("cityid") != null && !request.getParameter("cityid").equals("undefined") && !request.getParameter("cityid").equals("0")) {
                cr = cr.add(Restrictions.eq("cityid.cityid", Integer.parseInt(request.getParameter("cityid"))));
            }
//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<PropertylocalityForm> list = (List<PropertylocalityForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (PropertylocalityForm propertylocalityForm : list) {
                SelectCombo obj = (new SelectCombo(propertylocalityForm.getPropertylocalityid(), propertylocalityForm.getName()));
                obj.setPid1(propertylocalityForm.getCityid().getCityid());
                al.add(obj);
//            al.add(new SelectCombo(propertylocalityForm.getPropertylocalityid(), propertylocalityForm.getName()));
            }
        }
        return al;
    }

    public void setFK(PropertylocalityForm propertylocalityForm) {

        if (propertylocalityForm.getSelectcityid() > 0) {
            propertylocalityForm.setCityid((CityForm) sessionfactory.getCurrentSession().get(CityForm.class, propertylocalityForm.getSelectcityid()));
        }

        if (propertylocalityForm.getSelectuserid() > 0) {
            propertylocalityForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, propertylocalityForm.getSelectuserid()));
        }
    }
}
