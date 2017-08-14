package admin.user;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import admin.filter.TableForm;
import admin.user.UserForm;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import admin.filter.CommonUtil;

import admin.role.RoleForm;
import pms.customer.CustomerForm;

/**
 *
 * @user Vision
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addUser(UserForm userForm) {
        setFK(userForm);
        sessionfactory.getCurrentSession().save(userForm);
    }

    @Override
    public TableForm getUserList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(UserForm.class
        );

        cr = cr.createAlias("roleid", "roleid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("email", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("password", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("confirmpassword", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("fullname", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("userid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(userName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<UserForm> list = (List<UserForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public UserForm editUser(Integer id) {
        UserForm userForm = (UserForm) sessionfactory.getCurrentSession().get(UserForm.class, id);

        if (userForm.getRoleid() != null) {
            userForm.setSelectroleid(userForm.getRoleid().getRoleid());
        }
        return userForm;
    }

    @Override
    public String updateUser(UserForm userForm) {
        String ret = "";
        if (userForm.getOption() != null && userForm.getOption().equals("pwd")) {
            UserForm userForm1 = (UserForm) sessionfactory.getCurrentSession().get(UserForm.class, userForm.getUserid());
            if (userForm.getOldpwd().equals(userForm1.getPassword())) {
                userForm1.setPassword(userForm.getPassword());
                sessionfactory.getCurrentSession().update(userForm1);
                return "Password updated successfully";
            } else {
                return "Old password is not Match.";
            }

        } else if (userForm.getOption() != null && userForm.getOption().equals("profile")) {
            UserForm userForm1 = (UserForm) sessionfactory.getCurrentSession().get(UserForm.class, userForm.getUserid());
            userForm1.setFullname(userForm.getFullname());
            userForm1.setEmail(userForm.getEmail());

            sessionfactory.getCurrentSession().update(userForm1);
            return "Profile updated successfully";
        } else {
            setFK(userForm);
            sessionfactory.getCurrentSession().update(userForm);
        }
        return "";
    }

    @Override
    public void deleteUser(Integer id) {
        UserForm userForm = (UserForm) sessionfactory.getCurrentSession().load(UserForm.class, id);

        if (userForm != null) {
            userForm.setActive("0");
            sessionfactory.getCurrentSession().update(userForm);
        }

    }

    @Override
    
    public List<SelectCombo> getUserComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select user." + request.getParameter("col") + " , count(*) as cnt from User user "
                    + "where user.active='1' "
                    + "group by user." + request.getParameter("col") + " order by user." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select user.userid,user.fullname,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".userid  user where user.userid = " + table.toLowerCase() + ".userid.userid and  user.active='1' group by user.userid,user.fullname  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select user.userid,user.fullname,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".userid  user where  user.active='1' group by user.userid,user.fullname  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(UserForm.class);

            if (request.getParameter("roleid") != null && !request.getParameter("roleid").equals("undefined") && !request.getParameter("roleid").equals("0")) {
                cr = cr.add(Restrictions.eq("roleid.roleid", Integer.parseInt(request.getParameter("roleid"))));
            }
            List<UserForm> list = (List<UserForm>) cr.addOrder(Order.asc("fullname")).add(Restrictions.ilike("active", "1")).list();

            for (UserForm userForm : list) {
                SelectCombo obj = (new SelectCombo(userForm.getUserid(), userForm.getFullname()));

                al.add(obj);
//            al.add(new SelectCombo(userForm.getUserid(), userForm.getFullname()));
            }
        }
        return al;
    }

    @Override
    public boolean checkExist(String col, String val, int id) {

        Criteria cr = sessionfactory.getCurrentSession().createCriteria(UserForm.class);
        cr = cr.add(Restrictions.eq(col, val));
        cr = cr.add(Restrictions.ne("customerid", id));

        if (cr.list().size() > 0) {
            return true;
        }
        return false;
    }

    public void setFK(UserForm userForm) {

        if (userForm.getSelectroleid() > 0) {
            userForm.setRoleid((RoleForm) sessionfactory.getCurrentSession().get(RoleForm.class, userForm.getSelectroleid()));
        }
    }
}
