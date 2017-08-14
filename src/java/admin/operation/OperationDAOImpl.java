package admin.operation;


import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import admin.filter.TableForm;
import admin.operation.OperationForm;
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
 * @operation Vision
 */
@Repository
public class OperationDAOImpl implements OperationDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addOperation(OperationForm operationForm) {
        setFK(operationForm);
        sessionfactory.getCurrentSession().save(operationForm);
    }

    @Override
    public TableForm getOperationList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(OperationForm.class
        );

        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("funname", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("url", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("operationid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(operationName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<OperationForm> list = (List<OperationForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public OperationForm editOperation(Integer id) {
        OperationForm operationForm = (OperationForm) sessionfactory.getCurrentSession().get(OperationForm.class, id);

        if (operationForm.getUserid() != null) {
            operationForm.setSelectuserid(operationForm.getUserid().getUserid());
        }
        return operationForm;
    }

    @Override
    public void updateOperation(OperationForm operationForm) {
        setFK(operationForm);
        sessionfactory.getCurrentSession().update(operationForm);
    }

    @Override
    public void deleteOperation(Integer id) {
        OperationForm operationForm = (OperationForm) sessionfactory.getCurrentSession().load(OperationForm.class, id);

        if (operationForm != null) {
            operationForm.setActive("0");
            sessionfactory.getCurrentSession().update(operationForm);
        }

    }

    @Override
    public List<SelectCombo> getOperationComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select operation." + request.getParameter("col") + " , count(*) as cnt from Operation operation "
//                    + "where operation.active='1' "
//                    + "group by operation." + request.getParameter("col") + " order by operation." + request.getParameter("col") + " ").list();
//            //  List list = (List) c.addOrder(Order.asc(col)).list();
//            //String data = "";
//            for (int i = 0; i < l.size(); i++) {
//                al.add(new SelectCombo(0,
//                        (String) l.get(i)[0],
//                        ((Long) l.get(i)[1]).intValue()));
//                //data += propertyForm + ",";
//                //      al.add(new SelectCombo(propertyForm.getPropertyid(), propertyForm.getFlatno()));
//            }
        } else if (request.getParameter("table") != null) {
//            String table = request.getParameter("table").toString().trim();
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select operation.operationid,operation.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".operationid  operation where operation.operationid = " + table.toLowerCase() + ".operationid.operationid and  operation.active='1' group by operation.operationid,operation.name  "
//                    + "order by cnt desc").list();
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select operation.operationid,operation.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "  join  " + table.toLowerCase() + ".operationid  operation where  operation.active='1' group by operation.operationid,operation.name  "
//                    + "order by cnt desc").list();
//            for (int i = 0; i < l.size(); i++) {
//                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
//                        (String) l.get(i)[1],
//                        ((Long) l.get(i)[2]).intValue()));
//            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(OperationForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<OperationForm> list = (List<OperationForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (OperationForm operationForm : list) {
                SelectCombo obj = (new SelectCombo(operationForm.getOperationid(), operationForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(operationForm.getOperationid(), operationForm.getName()));
            }
        }
        return al;
    }

    public void setFK(OperationForm operationForm) {

        if (operationForm.getSelectuserid() > 0) {
            operationForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, operationForm.getSelectuserid()));
        }
    }
}
