package pms.property;

import pms.property.PropertyForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.property.PropertyForm;
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

import pms.propertyfurnished.PropertyfurnishedForm;
import pms.city.CityForm;
import pms.propertytype.PropertytypeForm;
import pms.propertylbedrooms.PropertylbedroomsForm;
import admin.file.FileForm;
import pms.propertytobe.PropertytobeForm;
import pms.flatamenities.FlatamenitiesForm;
import pms.societyamenities.SocietyamenitiesForm;
import pms.propertypriority.PropertypriorityForm;
import pms.propertylocality.PropertylocalityForm;
import pms.propertyfacing.PropertyfacingForm;
import admin.user.UserForm;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import pms.customer.CustomerForm;

/**
 *
 * @property Vision
 */
@Repository
public class PropertyDAOImpl implements PropertyDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addProperty(PropertyForm propertyForm) {
        setFK(propertyForm);
        sessionfactory.getCurrentSession().save(propertyForm);
    }

    @Override
    public boolean duplicate(PropertyForm propertyForm) {
        setFK(propertyForm);
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertyForm.class
        );
        cr = cr.createAlias("customerid", "customerid");
        cr = cr.add(Restrictions.eq("customerid.mobileno", propertyForm.getCustomerid().getMobileno()));
        cr = cr.add(Restrictions.eq("propertylocalityid.propertylocalityid", propertyForm.getPropertylocalityid().getPropertylocalityid()));
        cr = cr.add(Restrictions.eq("propertylbedroomsid.propertylbedroomsid", propertyForm.getPropertylbedroomsid().getPropertylbedroomsid()));
        //  sessionfactory.getCurrentSession().save(propertyForm);
        return cr.list().size() > 0;
    }

    @Override
    public TableForm getPropertyList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertyForm.class
        );
//         if ((tableForm.getFilter().indexOf("propertypriorityid.propertypriorityid|undefined") != -1 )|| tableForm.getOrder().indexOf("propertypriorityid") != -1) {

        if ((tableForm.getFilter().indexOf("propertytypeid") != -1) || tableForm.getOrder().indexOf("propertylocalityid") != -1) {
            cr = cr.createAlias("propertytypeid", "propertytypeid");
        }
        if ((tableForm.getFilter().indexOf("customerid") != -1) || tableForm.getOrder().indexOf("customerid") != -1) {
            cr = cr.createAlias("customerid", "customerid");
        }
        if ((tableForm.getFilter().indexOf("propertypriorityid") != -1) || tableForm.getOrder().indexOf("propertypriorityid") != -1) {
            cr = cr.createAlias("propertypriorityid", "propertypriorityid");
        }
//        if (tableForm.getFilter().indexOf("cityid") != -1 || tableForm.getOrder().indexOf("cityid") != -1) {
//            cr = cr.createAlias("cityid", "cityid");
//        }
        if ((tableForm.getFilter().indexOf("propertylocalityid") != -1) || tableForm.getOrder().indexOf("propertylocalityid") != -1) {
            cr = cr.createAlias("propertylocalityid", "propertylocalityid");
        }
        if ((tableForm.getFilter().indexOf("propertylbedroomsid") != -1) || tableForm.getOrder().indexOf("propertylbedroomsid") != -1) {
            cr = cr.createAlias("propertylbedroomsid", "propertylbedroomsid");
        }
        if ((tableForm.getFilter().indexOf("propertyfacingid") != -1) || tableForm.getOrder().indexOf("propertyfacingid") != -1) {
            cr = cr.createAlias("propertyfacingid", "propertyfacingid");
        }
        if ((tableForm.getFilter().indexOf("propertyfurnishedid") != -1) || tableForm.getOrder().indexOf("propertyfurnishedid") != -1) {
            cr = cr.createAlias("propertyfurnishedid", "propertyfurnishedid");
        }

        cr = cr.createAlias("propertytobeid", "propertytobeid");

        if ((tableForm.getFilter().indexOf("flatamenitiesid") != -1)) {
            cr = cr.createAlias("flatamenitiesid", "flatamenitiesid");
        }
        if ((tableForm.getFilter().indexOf("societyamenitiesid") != -1)) {
            cr = cr.createAlias("societyamenitiesid", "societyamenitiesid");
        }
//        if (tableForm.getFilter().indexOf("closedate") != -1) {
//            cr = cr.createAlias("propertydateid", "propertydateid");
//        } 
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("totalarea", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("flatno", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("apt", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("address", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("landmark", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("propertyage", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("leaseperiod", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("floorno", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("remark", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("sqfeetrate", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("balcony", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("bathroom", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("totalfloorno", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("keyavailability", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("keyname", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("keymobile", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("keyaddress", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("propertyid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.like("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<PropertyForm> listRet = new ArrayList();
        List<PropertyForm> list = (List<PropertyForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {
            PropertyForm obj = new PropertyForm(list.get(i));

            listRet.add(obj);

        }
        tableForm.setItems(listRet);
        return tableForm;
    }

    @Override
    public PropertyForm editProperty(Integer id) {
        PropertyForm propertyForm = (PropertyForm) sessionfactory.getCurrentSession().get(PropertyForm.class, id);

        if (propertyForm.getPropertytypeid() != null) {
            propertyForm.setSelectpropertytypeid(propertyForm.getPropertytypeid().getPropertytypeid());
        }
        if (propertyForm.getCustomerid() != null) {
            propertyForm.setSelectcustomerid(propertyForm.getCustomerid().getCustomerid());
        }
        if (propertyForm.getPropertypriorityid() != null) {
            Object[] obj = propertyForm.getPropertypriorityid().toArray();
            propertyForm.setSelectpropertypriorityid(new int[propertyForm.getPropertypriorityid().size()]);
            for (int i = 0; i < propertyForm.getPropertypriorityid().size(); i++) {
                propertyForm.getSelectpropertypriorityid()[i] = ((PropertypriorityForm) obj[i]).getPropertypriorityid();
            }
        }

        if (propertyForm.getCityid() != null) {
            propertyForm.setSelectcityid(propertyForm.getCityid().getCityid());
        }
        if (propertyForm.getPropertylocalityid() != null) {
            propertyForm.setSelectpropertylocalityid(propertyForm.getPropertylocalityid().getPropertylocalityid());
        }
        if (propertyForm.getPropertylbedroomsid() != null) {
            propertyForm.setSelectpropertylbedroomsid(propertyForm.getPropertylbedroomsid().getPropertylbedroomsid());
        }
        if (propertyForm.getPropertyfacingid() != null) {
            propertyForm.setSelectpropertyfacingid(propertyForm.getPropertyfacingid().getPropertyfacingid());
        }
        if (propertyForm.getPropertyfurnishedid() != null) {
            propertyForm.setSelectpropertyfurnishedid(propertyForm.getPropertyfurnishedid().getPropertyfurnishedid());
        }
        if (propertyForm.getImage1() != null) {
            propertyForm.setSelectimage1(propertyForm.getImage1().getFileid());
        }
        if (propertyForm.getImage2() != null) {
            propertyForm.setSelectimage2(propertyForm.getImage2().getFileid());
        }
        if (propertyForm.getImage3() != null) {
            propertyForm.setSelectimage3(propertyForm.getImage3().getFileid());
        }
        if (propertyForm.getImage4() != null) {
            propertyForm.setSelectimage4(propertyForm.getImage4().getFileid());
        }
        if (propertyForm.getImage5() != null) {
            propertyForm.setSelectimage5(propertyForm.getImage5().getFileid());
        }
        if (propertyForm.getUserid() != null) {
            propertyForm.setSelectuserid(propertyForm.getUserid().getUserid());
        }
        if (propertyForm.getImage6() != null) {
            propertyForm.setSelectimage6(propertyForm.getImage6().getFileid());
        }
        if (propertyForm.getImage7() != null) {
            propertyForm.setSelectimage7(propertyForm.getImage7().getFileid());
        }
        if (propertyForm.getImage8() != null) {
            propertyForm.setSelectimage8(propertyForm.getImage8().getFileid());
        }
        if (propertyForm.getImage9() != null) {
            propertyForm.setSelectimage9(propertyForm.getImage9().getFileid());
        }
        if (propertyForm.getImage10() != null) {
            propertyForm.setSelectimage10(propertyForm.getImage10().getFileid());
        }
        if (propertyForm.getPropertytobeid() != null) {
            propertyForm.setSelectpropertytobeid(propertyForm.getPropertytobeid().getPropertytobeid());
        }
        if (propertyForm.getFlatamenitiesid() != null) {
            Object[] obj = propertyForm.getFlatamenitiesid().toArray();
            propertyForm.setSelectflatamenitiesid(new int[propertyForm.getFlatamenitiesid().size()]);
            for (int i = 0; i < propertyForm.getFlatamenitiesid().size(); i++) {
                propertyForm.getSelectflatamenitiesid()[i] = ((FlatamenitiesForm) obj[i]).getFlatamenitiesid();
            }
        }
        if (propertyForm.getSocietyamenitiesid() != null) {
            Object[] obj = propertyForm.getSocietyamenitiesid().toArray();
            propertyForm.setSelectsocietyamenitiesid(new int[propertyForm.getSocietyamenitiesid().size()]);
            for (int i = 0; i < propertyForm.getSocietyamenitiesid().size(); i++) {
                propertyForm.getSelectsocietyamenitiesid()[i] = ((SocietyamenitiesForm) obj[i]).getSocietyamenitiesid();
            }
        }
        return propertyForm;
    }

    @Override
    public void updateProperty(PropertyForm propertyForm) {
        setFK(propertyForm);
        if (propertyForm.getStatus()) {
            updateProperty(propertyForm, true);
        }
        sessionfactory.getCurrentSession().update(propertyForm);
    }

    @Override
    public void deleteProperty(Integer id) {
        PropertyForm propertyForm = (PropertyForm) sessionfactory.getCurrentSession().load(PropertyForm.class, id);

        if (propertyForm != null) {
            propertyForm.setActive("0");
            sessionfactory.getCurrentSession().update(propertyForm);
        }

    }

    @Override
    public List<SelectCombo> getPropertyComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select property." + request.getParameter("col") + " , count(*) as cnt from Property property "
                    + "where property.active='1' "
                    + "group by property." + request.getParameter("col") + " order by property." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select property.propertyid,property.flatno,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".propertyid  property where property.propertyid = " + table.toLowerCase() + ".propertyid.propertyid and  property.active='1' group by property.propertyid,property.flatno  "
//                    + "order by cnt desc").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select property.propertyid,property.flatno,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".propertyid  property where  property.active='1' group by property.propertyid,property.flatno  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertyForm.class);

            if (request.getParameter("propertytypeid") != null && !request.getParameter("propertytypeid").equals("undefined") && !request.getParameter("propertytypeid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertytypeid.propertytypeid", Integer.parseInt(request.getParameter("propertytypeid"))));
            }
            if (request.getParameter("customerid") != null && !request.getParameter("customerid").equals("undefined") && !request.getParameter("customerid").equals("0")) {
                cr = cr.add(Restrictions.eq("customerid.customerid", Integer.parseInt(request.getParameter("customerid"))));
            }
            if (request.getParameter("propertypriorityid") != null && !request.getParameter("propertypriorityid").equals("undefined") && !request.getParameter("propertypriorityid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertypriorityid.propertypriorityid", Integer.parseInt(request.getParameter("propertypriorityid"))));
            }
            if (request.getParameter("cityid") != null && !request.getParameter("cityid").equals("undefined") && !request.getParameter("cityid").equals("0")) {
                cr = cr.add(Restrictions.eq("cityid.cityid", Integer.parseInt(request.getParameter("cityid"))));
            }
            if (request.getParameter("propertylocalityid") != null && !request.getParameter("propertylocalityid").equals("undefined") && !request.getParameter("propertylocalityid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertylocalityid.propertylocalityid", Integer.parseInt(request.getParameter("propertylocalityid"))));
            }
            if (request.getParameter("propertylbedroomsid") != null && !request.getParameter("propertylbedroomsid").equals("undefined") && !request.getParameter("propertylbedroomsid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertylbedroomsid.propertylbedroomsid", Integer.parseInt(request.getParameter("propertylbedroomsid"))));
            }
            if (request.getParameter("propertyfacingid") != null && !request.getParameter("propertyfacingid").equals("undefined") && !request.getParameter("propertyfacingid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertyfacingid.propertyfacingid", Integer.parseInt(request.getParameter("propertyfacingid"))));
            }
            if (request.getParameter("propertyfurnishedid") != null && !request.getParameter("propertyfurnishedid").equals("undefined") && !request.getParameter("propertyfurnishedid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertyfurnishedid.propertyfurnishedid", Integer.parseInt(request.getParameter("propertyfurnishedid"))));
            }
            if (request.getParameter("image1") != null && !request.getParameter("image1").equals("undefined") && !request.getParameter("image1").equals("0")) {
                cr = cr.add(Restrictions.eq("image1.image1", Integer.parseInt(request.getParameter("image1"))));
            }
            if (request.getParameter("image2") != null && !request.getParameter("image2").equals("undefined") && !request.getParameter("image2").equals("0")) {
                cr = cr.add(Restrictions.eq("image2.image2", Integer.parseInt(request.getParameter("image2"))));
            }
            if (request.getParameter("image3") != null && !request.getParameter("image3").equals("undefined") && !request.getParameter("image3").equals("0")) {
                cr = cr.add(Restrictions.eq("image3.image3", Integer.parseInt(request.getParameter("image3"))));
            }
            if (request.getParameter("image4") != null && !request.getParameter("image4").equals("undefined") && !request.getParameter("image4").equals("0")) {
                cr = cr.add(Restrictions.eq("image4.image4", Integer.parseInt(request.getParameter("image4"))));
            }
            if (request.getParameter("image5") != null && !request.getParameter("image5").equals("undefined") && !request.getParameter("image5").equals("0")) {
                cr = cr.add(Restrictions.eq("image5.image5", Integer.parseInt(request.getParameter("image5"))));
            }
//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            if (request.getParameter("image6") != null && !request.getParameter("image6").equals("undefined") && !request.getParameter("image6").equals("0")) {
                cr = cr.add(Restrictions.eq("image6.image6", Integer.parseInt(request.getParameter("image6"))));
            }
            if (request.getParameter("image7") != null && !request.getParameter("image7").equals("undefined") && !request.getParameter("image7").equals("0")) {
                cr = cr.add(Restrictions.eq("image7.image7", Integer.parseInt(request.getParameter("image7"))));
            }
            if (request.getParameter("image8") != null && !request.getParameter("image8").equals("undefined") && !request.getParameter("image8").equals("0")) {
                cr = cr.add(Restrictions.eq("image8.image8", Integer.parseInt(request.getParameter("image8"))));
            }
            if (request.getParameter("image9") != null && !request.getParameter("image9").equals("undefined") && !request.getParameter("image9").equals("0")) {
                cr = cr.add(Restrictions.eq("image9.image9", Integer.parseInt(request.getParameter("image9"))));
            }
            if (request.getParameter("image10") != null && !request.getParameter("image10").equals("undefined") && !request.getParameter("image10").equals("0")) {
                cr = cr.add(Restrictions.eq("image10.image10", Integer.parseInt(request.getParameter("image10"))));
            }
            if (request.getParameter("propertytobeid") != null && !request.getParameter("propertytobeid").equals("undefined") && !request.getParameter("propertytobeid").equals("0")) {
                cr = cr.add(Restrictions.eq("propertytobeid.propertytobeid", Integer.parseInt(request.getParameter("propertytobeid"))));
            }
            List<PropertyForm> list = (List<PropertyForm>) cr.addOrder(Order.asc("flatno")).add(Restrictions.ilike("active", "1")).list();

            for (PropertyForm propertyForm : list) {
                SelectCombo obj = (new SelectCombo(propertyForm.getPropertyid(), propertyForm.getFlatno()));

                al.add(obj);
//            al.add(new SelectCombo(propertyForm.getPropertyid(), propertyForm.getFlatno()));
            }
        }
        return al;
    }

    public void setFK(PropertyForm propertyForm) {

        if (propertyForm.getSelectpropertytypeid() > 0) {
            propertyForm.setPropertytypeid((PropertytypeForm) sessionfactory.getCurrentSession().get(PropertytypeForm.class, propertyForm.getSelectpropertytypeid()));
        }

        if (propertyForm.getSelectcustomerid() > 0) {
            propertyForm.setCustomerid((CustomerForm) sessionfactory.getCurrentSession().get(CustomerForm.class, propertyForm.getSelectcustomerid()));
        }

        if (propertyForm.getSelectpropertypriorityid() != null) {
            propertyForm.setPropertypriorityid(new ArrayList());
            for (int i = 0; i < propertyForm.getSelectpropertypriorityid().length; i++) {
                propertyForm.getPropertypriorityid().add((PropertypriorityForm) sessionfactory.getCurrentSession().get(PropertypriorityForm.class, propertyForm.getSelectpropertypriorityid()[i]));
            }
        }

        if (propertyForm.getSelectcityid() > 0) {
            propertyForm.setCityid((CityForm) sessionfactory.getCurrentSession().get(CityForm.class, propertyForm.getSelectcityid()));
        }

        if (propertyForm.getSelectpropertylocalityid() > 0) {
            propertyForm.setPropertylocalityid((PropertylocalityForm) sessionfactory.getCurrentSession().get(PropertylocalityForm.class, propertyForm.getSelectpropertylocalityid()));
        }

        if (propertyForm.getSelectpropertylbedroomsid() > 0) {
            propertyForm.setPropertylbedroomsid((PropertylbedroomsForm) sessionfactory.getCurrentSession().get(PropertylbedroomsForm.class, propertyForm.getSelectpropertylbedroomsid()));
        }

        if (propertyForm.getSelectpropertyfacingid() > 0) {
            propertyForm.setPropertyfacingid((PropertyfacingForm) sessionfactory.getCurrentSession().get(PropertyfacingForm.class, propertyForm.getSelectpropertyfacingid()));
        }

        if (propertyForm.getSelectpropertyfurnishedid() > 0) {
            propertyForm.setPropertyfurnishedid((PropertyfurnishedForm) sessionfactory.getCurrentSession().get(PropertyfurnishedForm.class, propertyForm.getSelectpropertyfurnishedid()));
        }

        if (propertyForm.getSelectimage1() > 0) {
            propertyForm.setImage1((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage1()));
        }

        if (propertyForm.getSelectimage2() > 0) {
            propertyForm.setImage2((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage2()));
        }

        if (propertyForm.getSelectimage3() > 0) {
            propertyForm.setImage3((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage3()));
        }

        if (propertyForm.getSelectimage4() > 0) {
            propertyForm.setImage4((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage4()));
        }

        if (propertyForm.getSelectimage5() > 0) {
            propertyForm.setImage5((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage5()));
        }

        if (propertyForm.getSelectuserid() > 0) {
            propertyForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, propertyForm.getSelectuserid()));
        }

        if (propertyForm.getSelectimage6() > 0) {
            propertyForm.setImage6((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage6()));
        }

        if (propertyForm.getSelectimage7() > 0) {
            propertyForm.setImage7((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage7()));
        }

        if (propertyForm.getSelectimage8() > 0) {
            propertyForm.setImage8((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage8()));
        }

        if (propertyForm.getSelectimage9() > 0) {
            propertyForm.setImage9((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage9()));
        }

        if (propertyForm.getSelectimage10() > 0) {
            propertyForm.setImage10((FileForm) sessionfactory.getCurrentSession().get(FileForm.class, propertyForm.getSelectimage10()));
        }

        if (propertyForm.getSelectpropertytobeid() > 0) {
            propertyForm.setPropertytobeid((PropertytobeForm) sessionfactory.getCurrentSession().get(PropertytobeForm.class, propertyForm.getSelectpropertytobeid()));
        }
        if (propertyForm.getSelectflatamenitiesid() != null) {
            propertyForm.setFlatamenitiesid(new ArrayList());
            for (int i = 0; i < propertyForm.getSelectflatamenitiesid().length; i++) {
                propertyForm.getFlatamenitiesid().add((FlatamenitiesForm) sessionfactory.getCurrentSession().get(FlatamenitiesForm.class, propertyForm.getSelectflatamenitiesid()[i]));
            }
        }
        if (propertyForm.getSelectsocietyamenitiesid() != null) {
            propertyForm.setSocietyamenitiesid(new ArrayList());
            for (int i = 0; i < propertyForm.getSelectsocietyamenitiesid().length; i++) {
                propertyForm.getSocietyamenitiesid().add((SocietyamenitiesForm) sessionfactory.getCurrentSession().get(SocietyamenitiesForm.class, propertyForm.getSelectsocietyamenitiesid()[i]));
            }
        }
    }

    @Override
    public void updateALLProperty() {
        int day = 60;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -day);
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(PropertyForm.class);
        cr = cr.createAlias("propertytobeid", "propertytobeid");
        cr = cr.add(Restrictions.lt("availablefrom", c.getTime()));
        cr = cr.add(Restrictions.eq("status", false));
        cr = cr.add(Restrictions.eq("propertytobeid.propertytobeid", 1));
        List<PropertyForm> list = cr.list();
        for (int i = 0; i < list.size(); i++) {
            updateProperty(list.get(i), true);
            //pr.setAvilabledate(list.get(i));
        }

        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -180);
        cr = sessionfactory.getCurrentSession().createCriteria(PropertyForm.class);
        cr = cr.createAlias("propertytobeid", "propertytobeid");
        cr = cr.add(Restrictions.lt("availablefrom", c.getTime()));
        cr = cr.add(Restrictions.eq("status", false));
        cr = cr.add(Restrictions.eq("propertytobeid.propertytobeid", 2));
        list = cr.list();
        for (int i = 0; i < list.size(); i++) {

            updateProperty(list.get(i), true);
            //pr.setAvilabledate(list.get(i));
        }

        c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, day);
        cr = sessionfactory.getCurrentSession().createCriteria(PropertyForm.class);
        cr = cr.createAlias("propertytobeid", "propertytobeid");

        cr = cr.add(Restrictions.gt("nextavilabledate", new Date()));
        cr = cr.add(Restrictions.lt("nextavilabledate", c.getTime()));
        cr = cr.add(Restrictions.eq("propertytobeid.propertytobeid", 1));
        cr = cr.add(Restrictions.eq("status", true));

        DetachedCriteria idlist = DetachedCriteria.forClass(PropertyForm.class)
                .setProjection(Property.forName("previd"));
        cr = cr.add(Property.forName("propertyid").notIn(idlist));

        list = cr.list();
        for (int i = 0; i < list.size(); i++) {
            PropertyForm p = new PropertyForm();
            p.setnewpropertyForm(list.get(i));
            p.setAvailablefrom(p.getNextavilabledate());
            p.setStatus(false);
            System.out.println("Next : " + p.getAvailablefrom() + ":" + p.getNextavilabledate());

            sessionfactory.getCurrentSession().save(p);

            //pr.setAvilabledate(list.get(i));
        }

    }

    public void updateProperty(PropertyForm propertyForm, boolean flag) {
        //  PropertyForm propertyForm1 = (PropertyForm) sessionfactory.getCurrentSession().get(PropertyForm.class, propertyForm.getPropertyid());
        if (flag) {
            propertyForm.setStatus(flag);

            propertyForm.status = true;
            Calendar c = Calendar.getInstance();

            propertyForm.setPostedon(propertyForm.getPostedon());
            propertyForm.setClosedate(new Date());
            propertyForm.setCloseby("Auto");

            if (propertyForm.getLeaseperiod() != null && !propertyForm.getLeaseperiod().equals("")) {
                try {
                    c.add(Calendar.MONTH, Integer.parseInt(propertyForm.getLeaseperiod()));
                } catch (Exception e) {
                    c.add(Calendar.MONTH, 11);
                }

            } else {
                c.add(Calendar.MONTH, 11);
            }
            propertyForm.setNextavilabledate(c.getTime());
            System.out.println("Next : " + propertyForm.getAvailablefrom() + ":" + propertyForm.getNextavilabledate());
            sessionfactory.getCurrentSession().update(propertyForm);

        }
    }

}
