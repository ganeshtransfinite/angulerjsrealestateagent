package pms.budgetrent;

import pms.budgetrent.BudgetrentForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.budgetrent.BudgetrentForm;
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
 * @budgetrent Vision
 */
@Repository
public class BudgetrentDAOImpl implements BudgetrentDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addBudgetrent(BudgetrentForm budgetrentForm) {
        setFK(budgetrentForm);
        sessionfactory.getCurrentSession().save(budgetrentForm);
    }

    @Override
    public TableForm getBudgetrentList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(BudgetrentForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("budgetrentid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(budgetrentName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<BudgetrentForm> list = (List<BudgetrentForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public BudgetrentForm editBudgetrent(Integer id) {
        BudgetrentForm budgetrentForm = (BudgetrentForm) sessionfactory.getCurrentSession().get(BudgetrentForm.class, id);

        if (budgetrentForm.getUserid() != null) {
            budgetrentForm.setSelectuserid(budgetrentForm.getUserid().getUserid());
        }
        return budgetrentForm;
    }

    @Override
    public void updateBudgetrent(BudgetrentForm budgetrentForm) {
        setFK(budgetrentForm);
        sessionfactory.getCurrentSession().update(budgetrentForm);
    }

    @Override
    public void deleteBudgetrent(Integer id) {
        BudgetrentForm budgetrentForm = (BudgetrentForm) sessionfactory.getCurrentSession().load(BudgetrentForm.class, id);

        if (budgetrentForm != null) {
            budgetrentForm.setActive("0");
            sessionfactory.getCurrentSession().update(budgetrentForm);
        }

    }

    @Override
    public List<SelectCombo> getBudgetrentComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select budgetrent." + request.getParameter("col") + " , count(*) as cnt from Budgetrent budgetrent "
                    + "where budgetrent.active='1' "
                    + "group by budgetrent." + request.getParameter("col") + " order by budgetrent." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select budgetrent.budgetrentid,budgetrent.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".budgetrentid  budgetrent where budgetrent.budgetrentid = " + table.toLowerCase() + ".budgetrentid.budgetrentid and  budgetrent.active='1' group by budgetrent.budgetrentid,budgetrent.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select budgetrent.budgetrentid,budgetrent.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".budgetrentid  budgetrent where  budgetrent.active='1' group by budgetrent.budgetrentid,budgetrent.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(BudgetrentForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<BudgetrentForm> list = (List<BudgetrentForm>) cr.addOrder(Order.asc("recordorder")).add(Restrictions.ilike("active", "1")).list();

            for (BudgetrentForm budgetrentForm : list) {
                SelectCombo obj = (new SelectCombo((int)budgetrentForm.getAmount() , budgetrentForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(budgetrentForm.getBudgetrentid(), budgetrentForm.getName()));
            }
        }
        return al;
    }

    public void setFK(BudgetrentForm budgetrentForm) {

        if (budgetrentForm.getSelectuserid() > 0) {
            budgetrentForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, budgetrentForm.getSelectuserid()));
        }
    }
}
