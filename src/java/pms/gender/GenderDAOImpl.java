package pms.gender;

import pms.gender.GenderForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.gender.GenderForm;
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
 * @gender Vision
 */
@Repository
public class GenderDAOImpl implements GenderDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addGender(GenderForm genderForm) {
        setFK(genderForm);
        sessionfactory.getCurrentSession().save(genderForm);
    }

    @Override
    public TableForm getGenderList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(GenderForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("genderid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(genderName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<GenderForm> list = (List<GenderForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public GenderForm editGender(Integer id) {
        GenderForm genderForm = (GenderForm) sessionfactory.getCurrentSession().get(GenderForm.class, id);

        if (genderForm.getUserid() != null) {
            genderForm.setSelectuserid(genderForm.getUserid().getUserid());
        }
        return genderForm;
    }

    @Override
    public void updateGender(GenderForm genderForm) {
        setFK(genderForm);
        sessionfactory.getCurrentSession().update(genderForm);
    }

    @Override
    public void deleteGender(Integer id) {
        GenderForm genderForm = (GenderForm) sessionfactory.getCurrentSession().load(GenderForm.class, id);

        if (genderForm != null) {
            genderForm.setActive("0");
            sessionfactory.getCurrentSession().update(genderForm);
        }

    }

    @Override
    public List<SelectCombo> getGenderComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select gender." + request.getParameter("col") + " , count(*) as cnt from Gender gender "
                    + "where gender.active='1' "
                    + "group by gender." + request.getParameter("col") + " order by gender." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select gender.genderid,gender.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".genderid  gender where gender.genderid = " + table.toLowerCase() + ".genderid.genderid and  gender.active='1' group by gender.genderid,gender.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select gender.genderid,gender.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".genderid  gender where  gender.active='1' group by gender.genderid,gender.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(GenderForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<GenderForm> list = (List<GenderForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (GenderForm genderForm : list) {
                SelectCombo obj = (new SelectCombo(genderForm.getGenderid(), genderForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(genderForm.getGenderid(), genderForm.getName()));
            }
        }
        return al;
    }

    public void setFK(GenderForm genderForm) {

        if (genderForm.getSelectuserid() > 0) {
            genderForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, genderForm.getSelectuserid()));
        }
    }
}
