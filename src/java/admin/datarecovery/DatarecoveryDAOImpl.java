package admin.datarecovery;

import admin.datarecovery.DatarecoveryForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import admin.datarecovery.DatarecoveryForm;
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
 * @datarecovery Vision
 */
@Repository
public class DatarecoveryDAOImpl implements DatarecoveryDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addDatarecovery(DatarecoveryForm datarecoveryForm) {
        setFK(datarecoveryForm);
        sessionfactory.getCurrentSession().save(datarecoveryForm);
    }

    @Override
    public TableForm getDatarecoveryList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(DatarecoveryForm.class
        );

        // Filedata
        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("tablename", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("datarecoveryid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(datarecoveryName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<DatarecoveryForm> list = (List<DatarecoveryForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContent(null);

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public DatarecoveryForm editDatarecovery(Integer id) {
        DatarecoveryForm datarecoveryForm = (DatarecoveryForm) sessionfactory.getCurrentSession().get(DatarecoveryForm.class, id);

//        if (datarecoveryForm.getContent() != null) {
//            datarecoveryForm.setSelectcontent(datarecoveryForm.getContent().getFileid());
//        }
        if (datarecoveryForm.getUserid() != null) {
            datarecoveryForm.setSelectuserid(datarecoveryForm.getUserid().getUserid());
        }
        return datarecoveryForm;
    }

    @Override
    public void updateDatarecovery(DatarecoveryForm datarecoveryForm) {
        setFK(datarecoveryForm);
        sessionfactory.getCurrentSession().update(datarecoveryForm);
    }

    @Override
    public void deleteDatarecovery(Integer id) {
        DatarecoveryForm datarecoveryForm = (DatarecoveryForm) sessionfactory.getCurrentSession().load(DatarecoveryForm.class, id);

        if (datarecoveryForm != null) {
            datarecoveryForm.setActive("0");
            sessionfactory.getCurrentSession().update(datarecoveryForm);
        }

    }

    @Override
    public List<SelectCombo> getDatarecoveryComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select datarecovery." + request.getParameter("col") + " , count(*) as cnt from Datarecovery datarecovery "
                    + "where datarecovery.active='1' "
                    + "group by datarecovery." + request.getParameter("col") + " order by datarecovery." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select datarecovery.datarecoveryid,datarecovery.tablename,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".datarecoveryid  datarecovery where datarecovery.datarecoveryid = " + table.toLowerCase() + ".datarecoveryid.datarecoveryid and  datarecovery.active='1' group by datarecovery.datarecoveryid,datarecovery.tablename  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select datarecovery.datarecoveryid,datarecovery.tablename,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".datarecoveryid  datarecovery where  datarecovery.active='1' group by datarecovery.datarecoveryid,datarecovery.tablename  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(DatarecoveryForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<DatarecoveryForm> list = (List<DatarecoveryForm>) cr.addOrder(Order.asc("tablename")).add(Restrictions.ilike("active", "1")).list();

            for (DatarecoveryForm datarecoveryForm : list) {
                SelectCombo obj = (new SelectCombo(datarecoveryForm.getDatarecoveryid(), datarecoveryForm.getTablename()));

                al.add(obj);
//            al.add(new SelectCombo(datarecoveryForm.getDatarecoveryid(), datarecoveryForm.getTablename()));
            }
        }
        return al;
    }

    public void setFK(DatarecoveryForm datarecoveryForm) {

        if (datarecoveryForm.getSelectuserid() > 0) {
            datarecoveryForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, datarecoveryForm.getSelectuserid()));
        }
    }
}
