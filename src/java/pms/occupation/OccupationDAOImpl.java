package pms.occupation;

import pms.occupation.OccupationForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.occupation.OccupationForm;
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
 * @occupation Vision
 */
@Repository
public class OccupationDAOImpl implements OccupationDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addOccupation(OccupationForm occupationForm) {
        setFK(occupationForm);
        sessionfactory.getCurrentSession().save(occupationForm);
    }

    @Override
    public TableForm getOccupationList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(OccupationForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("occupationid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(occupationName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<OccupationForm> list = (List<OccupationForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public OccupationForm editOccupation(Integer id) {
        OccupationForm occupationForm = (OccupationForm) sessionfactory.getCurrentSession().get(OccupationForm.class, id);

        if (occupationForm.getUserid() != null) {
            occupationForm.setSelectuserid(occupationForm.getUserid().getUserid());
        }
        return occupationForm;
    }

    @Override
    public void updateOccupation(OccupationForm occupationForm) {
        setFK(occupationForm);
        sessionfactory.getCurrentSession().update(occupationForm);
    }

    @Override
    public void deleteOccupation(Integer id) {
        OccupationForm occupationForm = (OccupationForm) sessionfactory.getCurrentSession().load(OccupationForm.class, id);

        if (occupationForm != null) {
            occupationForm.setActive("0");
            sessionfactory.getCurrentSession().update(occupationForm);
        }

    }

    @Override
    public List<SelectCombo> getOccupationComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select occupation." + request.getParameter("col") + " , count(*) as cnt from Occupation occupation "
                    + "where occupation.active='1' "
                    + "group by occupation." + request.getParameter("col") + " order by occupation." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select occupation.occupationid,occupation.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".occupationid  occupation where occupation.occupationid = " + table.toLowerCase() + ".occupationid.occupationid and  occupation.active='1' group by occupation.occupationid,occupation.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select occupation.occupationid,occupation.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".occupationid  occupation where  occupation.active='1' group by occupation.occupationid,occupation.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(OccupationForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<OccupationForm> list = (List<OccupationForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (OccupationForm occupationForm : list) {
                SelectCombo obj = (new SelectCombo(occupationForm.getOccupationid(), occupationForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(occupationForm.getOccupationid(), occupationForm.getName()));
            }
        }
        return al;
    }

    public void setFK(OccupationForm occupationForm) {

        if (occupationForm.getSelectuserid() > 0) {
            occupationForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, occupationForm.getSelectuserid()));
        }
    }
}
