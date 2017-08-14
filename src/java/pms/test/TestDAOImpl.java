package pms.test;

import pms.test.TestForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.test.TestForm;
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

import pms.flatamenities.FlatamenitiesForm;
import pms.societyamenities.SocietyamenitiesForm;
import admin.user.UserForm;

/**
 *
 * @test Vision
 */
@Repository
public class TestDAOImpl implements TestDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addTest(TestForm testForm) {
        setFK(testForm);
        sessionfactory.getCurrentSession().save(testForm);
    }

    @Override
    public TableForm getTestList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(TestForm.class
        );

        cr = cr.createAlias("flatamenitiesid", "flatamenitiesid");
        cr = cr.createAlias("societyamenitiesid", "societyamenitiesid");
        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("testid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(testName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<TestForm> list = (List<TestForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public TestForm editTest(Integer id) {
        TestForm testForm = (TestForm) sessionfactory.getCurrentSession().get(TestForm.class, id);

        if (testForm.getFlatamenitiesid() != null) {
            Object[] obj = testForm.getFlatamenitiesid().toArray();
            testForm.setSelectflatamenitiesid(new int[testForm.getFlatamenitiesid().size()]);
            for (int i = 0; i < testForm.getFlatamenitiesid().size(); i++) {
                testForm.getSelectflatamenitiesid()[i] = ((FlatamenitiesForm) obj[i]).getFlatamenitiesid();
            }
        }
        if (testForm.getSocietyamenitiesid() != null) {
            Object[] obj = testForm.getSocietyamenitiesid().toArray();
            testForm.setSelectsocietyamenitiesid(new int[testForm.getSocietyamenitiesid().size()]);
            for (int i = 0; i < testForm.getSocietyamenitiesid().size(); i++) {
                testForm.getSelectsocietyamenitiesid()[i] = ((SocietyamenitiesForm) obj[i]).getSocietyamenitiesid();
            }
        }
        if (testForm.getUserid() != null) {
            testForm.setSelectuserid(testForm.getUserid().getUserid());
        }
        return testForm;
    }

    @Override
    public void updateTest(TestForm testForm) {
        setFK(testForm);
        sessionfactory.getCurrentSession().update(testForm);
    }

    @Override
    public void deleteTest(Integer id) {
        TestForm testForm = (TestForm) sessionfactory.getCurrentSession().load(TestForm.class, id);

        if (testForm != null) {
            testForm.setActive("0");
            sessionfactory.getCurrentSession().update(testForm);
        }

    }

    @Override
    public List<SelectCombo> getTestComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select test." + request.getParameter("col") + " , count(*) as cnt from Test test "
                    + "where test.active='1' "
                    + "group by test." + request.getParameter("col") + " order by test." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select test.testid,test.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".testid  test where test.testid = " + table.toLowerCase() + ".testid.testid and  test.active='1' group by test.testid,test.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select test.testid,test.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".testid  test where  test.active='1' group by test.testid,test.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(TestForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<TestForm> list = (List<TestForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (TestForm testForm : list) {
                SelectCombo obj = (new SelectCombo(testForm.getTestid(), testForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(testForm.getTestid(), testForm.getName()));
            }
        }
        return al;
    }

    public void setFK(TestForm testForm) {

        if (testForm.getSelectflatamenitiesid() != null) {
            testForm.setFlatamenitiesid(new ArrayList());
            for (int i = 0; i < testForm.getSelectflatamenitiesid().length; i++) {
                testForm.getFlatamenitiesid().add((FlatamenitiesForm) sessionfactory.getCurrentSession().get(FlatamenitiesForm.class, testForm.getSelectflatamenitiesid()[i]));
            }
        }
        if (testForm.getSelectsocietyamenitiesid() != null) {
            testForm.setSocietyamenitiesid(new ArrayList());
            for (int i = 0; i < testForm.getSelectsocietyamenitiesid().length; i++) {
                testForm.getSocietyamenitiesid().add((SocietyamenitiesForm) sessionfactory.getCurrentSession().get(SocietyamenitiesForm.class, testForm.getSelectsocietyamenitiesid()[i]));
            }
        }

        if (testForm.getSelectuserid() > 0) {
            testForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, testForm.getSelectuserid()));
        }
    }
}
