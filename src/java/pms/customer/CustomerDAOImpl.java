package pms.customer;

import pms.customer.CustomerForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.customer.CustomerForm;
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

import pms.occupation.OccupationForm;
import pms.gender.GenderForm;
import pms.city.CityForm;
import pms.customertype.CustomertypeForm;
import admin.user.UserForm;

/**
 *
 * @customer Vision
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addCustomer(CustomerForm customerForm) {
        setFK(customerForm);
        sessionfactory.getCurrentSession().save(customerForm);
    }

    @Override
    public TableForm getCustomerList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(CustomerForm.class
        );

        cr = cr.createAlias("customertypeid", "customertypeid", Criteria.LEFT_JOIN);
        cr = cr.createAlias("occupationid", "occupationid", Criteria.LEFT_JOIN);
        cr = cr.createAlias("genderid", "genderid", Criteria.LEFT_JOIN);
        cr = cr.createAlias("cityid", "cityid", Criteria.LEFT_JOIN);
        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("fullname", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("email", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("mobileno", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("landlineno", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("address", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("pincode", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("customerid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(customerName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<CustomerForm> list = (List<CustomerForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public CustomerForm editCustomer(Integer id) {
        CustomerForm customerForm = (CustomerForm) sessionfactory.getCurrentSession().get(CustomerForm.class, id);

        if (customerForm.getCustomertypeid() != null) {
            customerForm.setSelectcustomertypeid(customerForm.getCustomertypeid().getCustomertypeid());
        }
        if (customerForm.getOccupationid() != null) {
            customerForm.setSelectoccupationid(customerForm.getOccupationid().getOccupationid());
        }
        if (customerForm.getGenderid() != null) {
            customerForm.setSelectgenderid(customerForm.getGenderid().getGenderid());
        }
        if (customerForm.getCityid() != null) {
            customerForm.setSelectcityid(customerForm.getCityid().getCityid());
        }
        if (customerForm.getUserid() != null) {
            customerForm.setSelectuserid(customerForm.getUserid().getUserid());
        }
        return customerForm;
    }

    @Override
    public void updateCustomer(CustomerForm customerForm) {
        setFK(customerForm);
        sessionfactory.getCurrentSession().update(customerForm);
    }

    @Override
    public void deleteCustomer(Integer id) {
        CustomerForm customerForm = (CustomerForm) sessionfactory.getCurrentSession().load(CustomerForm.class, id);

        if (customerForm != null) {
            customerForm.setActive("0");
            sessionfactory.getCurrentSession().update(customerForm);
        }

    }

    @Override
    public List<SelectCombo> getCustomerComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select customer." + request.getParameter("col") + " , count(*) as cnt from Customer customer "
                    + "where customer.active='1' "
                    + "group by customer." + request.getParameter("col") + " order by customer." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select customer.customerid,customer.fullname,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".customerid  customer where customer.customerid = " + table.toLowerCase() + ".customerid.customerid and  customer.active='1' group by customer.customerid,customer.fullname  "
//                    + "order by cnt desc").list();
            int type = 1;
            boolean status = false;
            if (request.getParameter("type").equals("1")) {
                status = false;
                type = 1;
            } else if (request.getParameter("type").equals("2")) {
                status = false;
                type = 2;
            } else if (request.getParameter("type").equals("3")) {
                status = true;
                type = 1;
            } else if (request.getParameter("type").equals("4")) {
                status = true;
                type = 2;
            }
            String cond = request.getParameter("table").equals("Property") ? " and " + table.toLowerCase() + ".status=" + status + " and " + table.toLowerCase() + ".propertytobeid.propertytobeid=" + type : " ";
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select customer.customerid,customer.fullname,count(" + table.toLowerCase() + ") as cnt,customer.mobileno from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".customerid  customer where  customer.active='1' "
                    + cond + " and " + table.toLowerCase() + ".active='1' "
                    + " group by customer.customerid,customer.fullname  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1]+"("+ l.get(i)[3]+")",
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(CustomerForm.class);

            if (request.getParameter("customertypeid") != null && !request.getParameter("customertypeid").equals("undefined") && !request.getParameter("customertypeid").equals("0")) {
                cr = cr.add(Restrictions.eq("customertypeid.customertypeid", Integer.parseInt(request.getParameter("customertypeid"))));
            }
            if (request.getParameter("occupationid") != null && !request.getParameter("occupationid").equals("undefined") && !request.getParameter("occupationid").equals("0")) {
                cr = cr.add(Restrictions.eq("occupationid.occupationid", Integer.parseInt(request.getParameter("occupationid"))));
            }
            if (request.getParameter("genderid") != null && !request.getParameter("genderid").equals("undefined") && !request.getParameter("genderid").equals("0")) {
                cr = cr.add(Restrictions.eq("genderid.genderid", Integer.parseInt(request.getParameter("genderid"))));
            }
            if (request.getParameter("cityid") != null && !request.getParameter("cityid").equals("undefined") && !request.getParameter("cityid").equals("0")) {
                cr = cr.add(Restrictions.eq("cityid.cityid", Integer.parseInt(request.getParameter("cityid"))));
            }
//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<CustomerForm> list = (List<CustomerForm>) cr.addOrder(Order.asc("fullname")).add(Restrictions.ilike("active", "1")).list();

            for (CustomerForm customerForm : list) {
                SelectCombo obj = (new SelectCombo(customerForm.getCustomerid(), customerForm.getFullname() +(customerForm.getMobileno() !=null && customerForm.getMobileno().length()>5 ? " ("+customerForm.getMobileno()+")" : "")));

                al.add(obj);
//            al.add(new SelectCombo(customerForm.getCustomerid(), customerForm.getFullname()));
            }
        }
        return al;
    }

    public void setFK(CustomerForm customerForm) {

        if (customerForm.getSelectcustomertypeid() > 0) {
            customerForm.setCustomertypeid((CustomertypeForm) sessionfactory.getCurrentSession().get(CustomertypeForm.class, customerForm.getSelectcustomertypeid()));
        }

        if (customerForm.getSelectoccupationid() > 0) {
            customerForm.setOccupationid((OccupationForm) sessionfactory.getCurrentSession().get(OccupationForm.class, customerForm.getSelectoccupationid()));
        }

        if (customerForm.getSelectgenderid() > 0) {
            customerForm.setGenderid((GenderForm) sessionfactory.getCurrentSession().get(GenderForm.class, customerForm.getSelectgenderid()));
        }

        if (customerForm.getSelectcityid() > 0) {
            customerForm.setCityid((CityForm) sessionfactory.getCurrentSession().get(CityForm.class, customerForm.getSelectcityid()));
        }

        if (customerForm.getSelectuserid() > 0) {
            customerForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, customerForm.getSelectuserid()));
        }
    }

    @Override
    public boolean checkExist(String col, String val, int id) {

        Criteria cr = sessionfactory.getCurrentSession().createCriteria(CustomerForm.class);
        cr = cr.add(Restrictions.eq(col, val));
        cr = cr.add(Restrictions.ne("customerid", id));

        if (cr.list().size() > 0) {
            return true;
        }
        return false;
    }
}
