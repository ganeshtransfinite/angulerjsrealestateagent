package admin.rolepermission;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import admin.filter.TableForm;
import admin.rolepermission.RolepermissionForm;
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
import admin.user.UserForm;
import admin.operation.OperationForm;

/**
 *
 * @rolepermission Vision
 */
@Repository
public class RolepermissionDAOImpl implements RolepermissionDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addRolepermission(RolepermissionForm rolepermissionForm) {
        setFK(rolepermissionForm);
        sessionfactory.getCurrentSession().save(rolepermissionForm);
    }

    @Override
    public TableForm getRolepermissionList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(RolepermissionForm.class
        );

        cr = cr.createAlias("roleid", "roleid", Criteria.LEFT_JOIN);
        cr = cr.createAlias("operationid", "operationid", Criteria.LEFT_JOIN);
        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("rolepermissionid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(rolepermissionName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<RolepermissionForm> list = (List<RolepermissionForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public RolepermissionForm editRolepermission(Integer id) {
        RolepermissionForm rolepermissionForm = (RolepermissionForm) sessionfactory.getCurrentSession().get(RolepermissionForm.class, id);

        if (rolepermissionForm.getRoleid() != null) {
            rolepermissionForm.setSelectroleid(rolepermissionForm.getRoleid().getRoleid());
        }
        if (rolepermissionForm.getOperationid() != null) {
            rolepermissionForm.setSelectoperationid(rolepermissionForm.getOperationid().getOperationid());
        }
        if (rolepermissionForm.getUserid() != null) {
            rolepermissionForm.setSelectuserid(rolepermissionForm.getUserid().getUserid());
        }
        return rolepermissionForm;
    }

    @Override
    public void updateRolepermission(RolepermissionForm rolepermissionForm) {
        setFK(rolepermissionForm);
        sessionfactory.getCurrentSession().update(rolepermissionForm);
    }

    @Override
    public void deleteRolepermission(Integer id) {
        RolepermissionForm rolepermissionForm = (RolepermissionForm) sessionfactory.getCurrentSession().load(RolepermissionForm.class, id);

        if (rolepermissionForm != null) {
            rolepermissionForm.setActive("0");
            sessionfactory.getCurrentSession().update(rolepermissionForm);
        }

    }

    @Override
    public List<SelectCombo> getRolepermissionComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select rolepermission." + request.getParameter("col") + " , count(*) as cnt from Rolepermission rolepermission "
                    + "where rolepermission.active='1' "
                    + "group by rolepermission." + request.getParameter("col") + " order by rolepermission." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select rolepermission.rolepermissionid,rolepermission.operationid,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".rolepermissionid  rolepermission where rolepermission.rolepermissionid = " + table.toLowerCase() + ".rolepermissionid.rolepermissionid and  rolepermission.active='1' group by rolepermission.rolepermissionid,rolepermission.operationid  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select rolepermission.rolepermissionid,rolepermission.operationid,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".rolepermissionid  rolepermission where  rolepermission.active='1' group by rolepermission.rolepermissionid,rolepermission.operationid  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(RolepermissionForm.class);

            if (request.getParameter("roleid") != null && !request.getParameter("roleid").equals("undefined") && !request.getParameter("roleid").equals("0")) {
                cr = cr.add(Restrictions.eq("roleid.roleid", Integer.parseInt(request.getParameter("roleid"))));
            }
            if (request.getParameter("operationid") != null && !request.getParameter("operationid").equals("undefined") && !request.getParameter("operationid").equals("0")) {
                cr = cr.add(Restrictions.eq("operationid.operationid", Integer.parseInt(request.getParameter("operationid"))));
            }
//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<RolepermissionForm> list = (List<RolepermissionForm>) cr.addOrder(Order.asc("operationid")).add(Restrictions.ilike("active", "1")).list();

            for (RolepermissionForm rolepermissionForm : list) {
                SelectCombo obj = (new SelectCombo(rolepermissionForm.getRolepermissionid(), rolepermissionForm.getOperationid().getName()));

                al.add(obj);
//            al.add(new SelectCombo(rolepermissionForm.getRolepermissionid(), rolepermissionForm.getOperationid()));
            }
        }
        return al;
    }

    public void setFK(RolepermissionForm rolepermissionForm) {

        if (rolepermissionForm.getSelectroleid() > 0) {
            rolepermissionForm.setRoleid((RoleForm) sessionfactory.getCurrentSession().get(RoleForm.class, rolepermissionForm.getSelectroleid()));
        }

        if (rolepermissionForm.getSelectoperationid() > 0) {
            rolepermissionForm.setOperationid((OperationForm) sessionfactory.getCurrentSession().get(OperationForm.class, rolepermissionForm.getSelectoperationid()));
        }

        if (rolepermissionForm.getSelectuserid() > 0) {
            rolepermissionForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, rolepermissionForm.getSelectuserid()));
        }
    }
    
    
    @Override
    public void updateRolepermission(int id, boolean val, String col) {
        RolepermissionForm rolepermissionForm = editRolepermission(id);
            rolepermissionForm.setPfunction(val);  
        
        sessionfactory.getCurrentSession().update(rolepermissionForm);
    }
}
