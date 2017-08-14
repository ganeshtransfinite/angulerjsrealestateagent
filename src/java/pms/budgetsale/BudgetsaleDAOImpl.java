package pms.budgetsale;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import admin.filter.TableForm;
import pms.budgetsale.BudgetsaleForm;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import admin.filter.CommonUtil;

import admin.user.UserForm;

/**
 *
 * @budgetsale Vision
 */
@Repository
public class BudgetsaleDAOImpl implements BudgetsaleDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addBudgetsale(BudgetsaleForm budgetsaleForm) {
        setFK(budgetsaleForm);
        sessionfactory.getCurrentSession().save(budgetsaleForm);
    }

    @Override
    public TableForm getBudgetsaleList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(BudgetsaleForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("budgetsaleid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(budgetsaleName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<BudgetsaleForm> list = (List<BudgetsaleForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public BudgetsaleForm editBudgetsale(Integer id) {
        BudgetsaleForm budgetsaleForm = (BudgetsaleForm) sessionfactory.getCurrentSession().get(BudgetsaleForm.class, id);

        if (budgetsaleForm.getUserid() != null) {
            budgetsaleForm.setSelectuserid(budgetsaleForm.getUserid().getUserid());
        }
        return budgetsaleForm;
    }

    @Override
    public void updateBudgetsale(BudgetsaleForm budgetsaleForm) {
        setFK(budgetsaleForm);
        sessionfactory.getCurrentSession().update(budgetsaleForm);
    }

    @Override
    public void deleteBudgetsale(Integer id) {
        BudgetsaleForm budgetsaleForm = (BudgetsaleForm) sessionfactory.getCurrentSession().load(BudgetsaleForm.class, id);

        if (budgetsaleForm != null) {
            budgetsaleForm.setActive("0");
            sessionfactory.getCurrentSession().update(budgetsaleForm);
        }

    }

    @Override
    public List<SelectCombo> getBudgetsaleComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select budgetsale." + request.getParameter("col") + " , count(*) as cnt from Budgetsale budgetsale "
                    + "where budgetsale.active='1' "
                    + "group by budgetsale." + request.getParameter("col") + " order by budgetsale." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select budgetsale.budgetsaleid,budgetsale.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".budgetsaleid  budgetsale where budgetsale.budgetsaleid = " + table.toLowerCase() + ".budgetsaleid.budgetsaleid and  budgetsale.active='1' group by budgetsale.budgetsaleid,budgetsale.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select budgetsale.budgetsaleid,budgetsale.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".budgetsaleid  budgetsale where  budgetsale.active='1' group by budgetsale.budgetsaleid,budgetsale.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(BudgetsaleForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<BudgetsaleForm> list = (List<BudgetsaleForm>) cr.addOrder(Order.asc("recordorder")).add(Restrictions.ilike("active", "1")).list();

            for (BudgetsaleForm budgetsaleForm : list) {
                SelectCombo obj = (new SelectCombo((int)budgetsaleForm.getAmount() , budgetsaleForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(budgetsaleForm.getBudgetsaleid(), budgetsaleForm.getName()));
            }
        }
        return al;
    }

    public void setFK(BudgetsaleForm budgetsaleForm) {

        if (budgetsaleForm.getSelectuserid() > 0) {
            budgetsaleForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, budgetsaleForm.getSelectuserid()));
        }
    }
}
