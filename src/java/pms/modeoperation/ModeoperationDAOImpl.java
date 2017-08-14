package pms.modeoperation;

import pms.modeoperation.ModeoperationForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.modeoperation.ModeoperationForm;
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
 * @modeoperation Vision
 */
@Repository
public class ModeoperationDAOImpl implements ModeoperationDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addModeoperation(ModeoperationForm modeoperationForm) {
        setFK(modeoperationForm);
        sessionfactory.getCurrentSession().save(modeoperationForm);
    }

    @Override
    public TableForm getModeoperationList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(ModeoperationForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("modeoperationid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(modeoperationName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<ModeoperationForm> list = (List<ModeoperationForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public ModeoperationForm editModeoperation(Integer id) {
        ModeoperationForm modeoperationForm = (ModeoperationForm) sessionfactory.getCurrentSession().get(ModeoperationForm.class, id);

        if (modeoperationForm.getUserid() != null) {
            modeoperationForm.setSelectuserid(modeoperationForm.getUserid().getUserid());
        }
        return modeoperationForm;
    }

    @Override
    public void updateModeoperation(ModeoperationForm modeoperationForm) {
        setFK(modeoperationForm);
        sessionfactory.getCurrentSession().update(modeoperationForm);
    }

    @Override
    public void deleteModeoperation(Integer id) {
        ModeoperationForm modeoperationForm = (ModeoperationForm) sessionfactory.getCurrentSession().load(ModeoperationForm.class, id);

        if (modeoperationForm != null) {
            modeoperationForm.setActive("0");
            sessionfactory.getCurrentSession().update(modeoperationForm);
        }

    }

    @Override
    public List<SelectCombo> getModeoperationComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select modeoperation." + request.getParameter("col") + " , count(*) as cnt from Modeoperation modeoperation "
                    + "where modeoperation.active='1' "
                    + "group by modeoperation." + request.getParameter("col") + " order by modeoperation." + request.getParameter("col") + " ").list();
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
            
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select modeoperation.modeoperationid,modeoperation.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".modeoperationid  modeoperation where modeoperation.modeoperationid = " + table.toLowerCase() + ".modeoperationid.modeoperationid and  modeoperation.active='1' group by modeoperation.modeoperationid,modeoperation.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select modeoperation.modeoperationid,modeoperation.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".modeoperationid  modeoperation where  modeoperation.active='1' group by modeoperation.modeoperationid,modeoperation.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(ModeoperationForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<ModeoperationForm> list = (List<ModeoperationForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (ModeoperationForm modeoperationForm : list) {
                SelectCombo obj = (new SelectCombo(modeoperationForm.getModeoperationid(), modeoperationForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(modeoperationForm.getModeoperationid(), modeoperationForm.getName()));
            }
        }
        return al;
    }

    public void setFK(ModeoperationForm modeoperationForm) {

        if (modeoperationForm.getSelectuserid() > 0) {
            modeoperationForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, modeoperationForm.getSelectuserid()));
        }
    }
}
