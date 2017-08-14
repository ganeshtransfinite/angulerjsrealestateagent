package pms.inquiry;

import pms.inquiry.InquiryForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.inquiry.InquiryForm;
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

import pms.city.CityForm;
import pms.propertylbedrooms.PropertylbedroomsForm;
import pms.propertypriority.PropertypriorityForm;
import pms.propertylocality.PropertylocalityForm;
import pms.propertyfacing.PropertyfacingForm;
import admin.user.UserForm;
import org.hibernate.sql.JoinType;
import pms.flatamenities.FlatamenitiesForm;
import pms.propertytobe.PropertytobeForm;
import pms.propertytype.PropertytypeForm;

/**
 *
 * @inquiry Vision
 */
@Repository
public class InquiryDAOImpl implements InquiryDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addInquiry(InquiryForm inquiryForm) {
        setFK(inquiryForm);
        sessionfactory.getCurrentSession().save(inquiryForm);
    }

    @Override
    public TableForm getInquiryList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(InquiryForm.class
        );
//,JoinType.LEFT_OUTER_JOIN

        if ((tableForm.getFilter().indexOf("propertypriorityid") != -1) || tableForm.getOrder().indexOf("propertypriorityid") != -1) {

            cr = cr.createAlias("propertypriorityid", "propertypriorityid");
        }
        if ((tableForm.getFilter().indexOf("propertylbedroomsid") != -1) || tableForm.getOrder().indexOf("propertylbedroomsid") != -1) {
            cr = cr.createAlias("propertylbedroomsid", "propertylbedroomsid");
        }
//        cr = cr.createAlias("cityid", "cityid");
        if ((tableForm.getFilter().indexOf("propertytobeid") != -1) || tableForm.getOrder().indexOf("propertytobeid") != -1) {
            cr = cr.createAlias("propertytobeid", "propertytobeid");
        }
        if ((tableForm.getFilter().indexOf("propertylocalityid") != -1) || tableForm.getOrder().indexOf("propertylocalityid") != -1) {
            cr = cr.createAlias("propertylocalityid", "propertylocalityid");
        }
        if ((tableForm.getFilter().indexOf("propertyfacingid") != -1) || tableForm.getOrder().indexOf("propertyfacingid") != -1) {

            cr = cr.createAlias("propertyfacingid", "propertyfacingid");
        }
        if ((tableForm.getFilter().indexOf("userid") != -1) || tableForm.getOrder().indexOf("userid") != -1) {

            cr = cr.createAlias("userid", "userid");
        }
        if ((tableForm.getFilter().indexOf("propertytypeid") != -1) || tableForm.getOrder().indexOf("propertytypeid") != -1) {

            cr = cr.createAlias("propertytypeid", "propertytypeid");
        }
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("mobileno", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("email", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        cr = cr.addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder()));
        List<InquiryForm> l = cr.list();
        tableForm.setTotal_count(l.size());
        int end = (tableForm.getPage()) * tableForm.getPer_page();
        if (end > l.size()) {
            end = l.size();
        }
        List<InquiryForm> list = new ArrayList();
        for (int i = (tableForm.getPage() - 1) * tableForm.getPer_page(); i < end; i++) {
            list.add(l.get(i));
        }
//        System.out.println( + ":" + );
//        .setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
//                setMaxResults(tableForm.getPer_page())
//.addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder()))
//        List<InquiryForm> list = (List<InquiryForm>) cr
//                .setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
//                setMaxResults(tableForm.getPer_page()).list();
//        for (int i = 0; i < list.size(); i++) {
//
//        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public InquiryForm editInquiry(Integer id) {
        InquiryForm inquiryForm = (InquiryForm) sessionfactory.getCurrentSession().get(InquiryForm.class, id);
        if (inquiryForm.getPropertypriorityid() != null) {
            Object[] obj = inquiryForm.getPropertypriorityid().toArray();
            inquiryForm.setSelectpropertypriorityid(new int[inquiryForm.getPropertypriorityid().size()]);
            for (int i = 0; i < inquiryForm.getPropertypriorityid().size(); i++) {
                inquiryForm.getSelectpropertypriorityid()[i] = ((PropertypriorityForm) obj[i]).getPropertypriorityid();
            }
        }
        if (inquiryForm.getPropertylbedroomsid() != null) {
            Object[] obj = inquiryForm.getPropertylbedroomsid().toArray();
            inquiryForm.setSelectpropertylbedroomsid(new int[inquiryForm.getPropertylbedroomsid().size()]);
            for (int i = 0; i < inquiryForm.getPropertylbedroomsid().size(); i++) {
                inquiryForm.getSelectpropertylbedroomsid()[i] = ((PropertylbedroomsForm) obj[i]).getPropertylbedroomsid();
            }
        }
        if (inquiryForm.getPropertylocalityid() != null) {
            Object[] obj = inquiryForm.getPropertylocalityid().toArray();
            inquiryForm.setSelectpropertylocalityid(new int[inquiryForm.getPropertylocalityid().size()]);
            for (int i = 0; i < inquiryForm.getPropertylocalityid().size(); i++) {
                inquiryForm.getSelectpropertylocalityid()[i] = ((PropertylocalityForm) obj[i]).getPropertylocalityid();
            }
        }

        if (inquiryForm.getPropertytobeid() != null) {
            Object[] obj = inquiryForm.getPropertytobeid().toArray();
            inquiryForm.setSelectpropertytobeid(new int[inquiryForm.getPropertytobeid().size()]);
            for (int i = 0; i < inquiryForm.getPropertytobeid().size(); i++) {
                inquiryForm.getSelectpropertytobeid()[i] = ((PropertytobeForm) obj[i]).getPropertytobeid();
            }
        }

        if (inquiryForm.getCityid() != null) {
            inquiryForm.setSelectcityid(inquiryForm.getCityid().getCityid());
        }

        if (inquiryForm.getPropertyfacingid() != null) {
            inquiryForm.setSelectpropertyfacingid(inquiryForm.getPropertyfacingid().getPropertyfacingid());
        }

        if (inquiryForm.getUserid() != null) {
            inquiryForm.setSelectuserid(inquiryForm.getUserid().getUserid());
        }

        if (inquiryForm.getPropertytypeid() != null) {
            Object[] obj = inquiryForm.getPropertytypeid().toArray();
            inquiryForm.setSelectpropertytypeid(new int[inquiryForm.getPropertytypeid().size()]);
            for (int i = 0; i < inquiryForm.getPropertytypeid().size(); i++) {
                inquiryForm.getSelectpropertytypeid()[i] = ((PropertytypeForm) obj[i]).getPropertytypeid();
            }
        }

        if (inquiryForm.getSelectpropertypriorityid() != null) {
            inquiryForm.setPropertypriorityid(new ArrayList());
            for (int i = 0; i < inquiryForm.getSelectpropertypriorityid().length; i++) {
                inquiryForm.getPropertypriorityid().add((PropertypriorityForm) sessionfactory.getCurrentSession().get(PropertypriorityForm.class, inquiryForm.getSelectpropertypriorityid()[i]));
            }
        }
        return inquiryForm;
    }

    @Override
    public void updateInquiry(InquiryForm inquiryForm) {
        setFK(inquiryForm);
        sessionfactory.getCurrentSession().update(inquiryForm);
    }

    @Override
    public void deleteInquiry(Integer id) {
        InquiryForm inquiryForm = (InquiryForm) sessionfactory.getCurrentSession().load(InquiryForm.class, id);

        if (inquiryForm != null) {
            inquiryForm.setActive("0");
            sessionfactory.getCurrentSession().update(inquiryForm);
        }

    }

    @Override
    public List<SelectCombo> getInquiryComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select inquiry." + request.getParameter("col") + " , count(*) as cnt from Inquiry inquiry "
                    + "where inquiry.active='1' "
                    + "group by inquiry." + request.getParameter("col") + " order by inquiry." + request.getParameter("col") + " ").list();
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

//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select inquiry.inquiryid,inquiry.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".inquiryid  inquiry where inquiry.inquiryid = " + table.toLowerCase() + ".inquiryid.inquiryid and  inquiry.active='1' group by inquiry.inquiryid,inquiry.name  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select inquiry.inquiryid,inquiry.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".inquiryid  inquiry where  inquiry.active='1' "
                    + cond + " and " + table.toLowerCase() + ".active='1' "
                    + " group by inquiry.inquiryid,inquiry.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(InquiryForm.class);

            if (request.getParameter("propertypriorityid") != null && !request.getParameter("propertypriorityid").equals("undefined") && !request.getParameter("propertypriorityid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertypriorityid.propertypriorityid", Integer.parseInt(request.getParameter("propertypriorityid"))));
            }
            if (request.getParameter("propertylbedroomsid") != null && !request.getParameter("propertylbedroomsid").equals("undefined") && !request.getParameter("propertylbedroomsid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertylbedroomsid.propertylbedroomsid", Integer.parseInt(request.getParameter("propertylbedroomsid"))));
            }
            if (request.getParameter("cityid") != null && !request.getParameter("cityid").equals("undefined") && !request.getParameter("cityid").equals("0")) {
                cr = cr.add(Restrictions.eq("cityid.cityid", Integer.parseInt(request.getParameter("cityid"))));
            }
            if (request.getParameter("propertylocalityid") != null && !request.getParameter("propertylocalityid").equals("undefined") && !request.getParameter("propertylocalityid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertylocalityid.propertylocalityid", Integer.parseInt(request.getParameter("propertylocalityid"))));
            }
            if (request.getParameter("propertyfacingid") != null && !request.getParameter("propertyfacingid").equals("undefined") && !request.getParameter("propertyfacingid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertyfacingid.propertyfacingid", Integer.parseInt(request.getParameter("propertyfacingid"))));
            }
//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            if (request.getParameter("propertytobeid") != null && !request.getParameter("propertytobeid").equals("undefined") && !request.getParameter("propertytobeid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertytobeid.propertytobeid", Integer.parseInt(request.getParameter("propertytobeid"))));
            }
            List<InquiryForm> list = (List<InquiryForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (InquiryForm inquiryForm : list) {
                SelectCombo obj = (new SelectCombo(inquiryForm.getInquiryid(), inquiryForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(inquiryForm.getInquiryid(), inquiryForm.getName()));
            }
        }
        return al;
    }

    public void setFK(InquiryForm inquiryForm) {

        if (inquiryForm.getSelectpropertypriorityid() != null) {
            inquiryForm.setPropertypriorityid(new ArrayList());
            for (int i = 0; i < inquiryForm.getSelectpropertypriorityid().length; i++) {
                inquiryForm.getPropertypriorityid().add((PropertypriorityForm) sessionfactory.getCurrentSession().get(PropertypriorityForm.class, inquiryForm.getSelectpropertypriorityid()[i]));
            }
        }
        if (inquiryForm.getSelectpropertylbedroomsid() != null) {
            inquiryForm.setPropertylbedroomsid(new ArrayList());
            for (int i = 0; i < inquiryForm.getSelectpropertylbedroomsid().length; i++) {
                inquiryForm.getPropertylbedroomsid().add((PropertylbedroomsForm) sessionfactory.getCurrentSession().get(PropertylbedroomsForm.class, inquiryForm.getSelectpropertylbedroomsid()[i]));
            }
        }
        if (inquiryForm.getSelectpropertylocalityid() != null) {
            inquiryForm.setPropertylocalityid(new ArrayList());
            for (int i = 0; i < inquiryForm.getSelectpropertylocalityid().length; i++) {
                inquiryForm.getPropertylocalityid().add((PropertylocalityForm) sessionfactory.getCurrentSession().get(PropertylocalityForm.class, inquiryForm.getSelectpropertylocalityid()[i]));
            }
        }

        if (inquiryForm.getSelectpropertytobeid() != null) {
            inquiryForm.setPropertytobeid(new ArrayList());
            for (int i = 0; i < inquiryForm.getSelectpropertytobeid().length; i++) {
                inquiryForm.getPropertytobeid().add((PropertytobeForm) sessionfactory.getCurrentSession().get(PropertytobeForm.class, inquiryForm.getSelectpropertytobeid()[i]));
            }
        }

        if (inquiryForm.getSelectcityid() > 0) {
            inquiryForm.setCityid((CityForm) sessionfactory.getCurrentSession().get(CityForm.class, inquiryForm.getSelectcityid()));
        }

        if (inquiryForm.getSelectpropertyfacingid() > 0) {
            inquiryForm.setPropertyfacingid((PropertyfacingForm) sessionfactory.getCurrentSession().get(PropertyfacingForm.class, inquiryForm.getSelectpropertyfacingid()));
        }

        if (inquiryForm.getSelectuserid() > 0) {
            inquiryForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, inquiryForm.getSelectuserid()));
        }
        if (inquiryForm.getSelectpropertytypeid() != null) {
            inquiryForm.setPropertytypeid(new ArrayList());
            for (int i = 0; i < inquiryForm.getSelectpropertytypeid().length; i++) {
                inquiryForm.getPropertytypeid().add((PropertytypeForm) sessionfactory.getCurrentSession().get(PropertytypeForm.class, inquiryForm.getSelectpropertytypeid()[i]));
            }
        }
    }
}
