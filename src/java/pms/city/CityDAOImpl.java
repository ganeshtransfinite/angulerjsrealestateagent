package pms.city;

import pms.city.CityForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.city.CityForm;
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
 * @city Vision
 */
@Repository
public class CityDAOImpl implements CityDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addCity(CityForm cityForm) {
        setFK(cityForm);
        sessionfactory.getCurrentSession().save(cityForm);
    }

    @Override
    public TableForm getCityList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(CityForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("cityid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(cityName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<CityForm> list = (List<CityForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public CityForm editCity(Integer id) {
        CityForm cityForm = (CityForm) sessionfactory.getCurrentSession().get(CityForm.class, id);

        if (cityForm.getUserid() != null) {
            cityForm.setSelectuserid(cityForm.getUserid().getUserid());
        }
        return cityForm;
    }

    @Override
    public void updateCity(CityForm cityForm) {
        setFK(cityForm);
        sessionfactory.getCurrentSession().update(cityForm);
    }

    @Override
    public void deleteCity(Integer id) {
        CityForm cityForm = (CityForm) sessionfactory.getCurrentSession().load(CityForm.class, id);

        if (cityForm != null) {
            cityForm.setActive("0");
            sessionfactory.getCurrentSession().update(cityForm);
        }

    }

    @Override
    public List<SelectCombo> getCityComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select city." + request.getParameter("col") + " , count(*) as cnt from City city "
                    + "where city.active='1' "
                    + "group by city." + request.getParameter("col") + " order by city." + request.getParameter("col") + " ").list();
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
            }   
            String table = request.getParameter("table").toString().trim();
            String cond = request.getParameter("table").equals("Property") ? " and " + table.toLowerCase() + ".status=" + status + " and " + table.toLowerCase() + ".propertytobeid.propertytobeid=" + type : " ";
          
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select city.cityid,city.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".cityid  city where city.cityid = " + table.toLowerCase() + ".cityid.cityid and  city.active='1' group by city.cityid,city.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select city.cityid,city.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".cityid  city where  city.active='1' "
                         +cond+" and "+table.toLowerCase() +".active='1' "
                    + " group by city.cityid,city.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(CityForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<CityForm> list = (List<CityForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (CityForm cityForm : list) {
                SelectCombo obj = (new SelectCombo(cityForm.getCityid(), cityForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(cityForm.getCityid(), cityForm.getName()));
            }
        }
        return al;
    }

    public void setFK(CityForm cityForm) {

        if (cityForm.getSelectuserid() > 0) {
            cityForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, cityForm.getSelectuserid()));
        }
    }
}
