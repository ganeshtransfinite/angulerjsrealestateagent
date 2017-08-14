package admin.role;

import admin.backup.FilRestore;
import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import admin.filter.TableForm;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import admin.filter.CommonUtil;
import admin.operation.OperationForm;
import admin.rolepermission.RolepermissionForm;

import admin.user.UserForm;
import java.util.Date;

/**
 *
 * @role Vision
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addRole(RoleForm roleForm) {
        setFK(roleForm);
        sessionfactory.getCurrentSession().save(roleForm);
        updateRoleTable(roleForm);
    }

    @Override
    public TableForm getRoleList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(RoleForm.class
        );

        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("roleid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(roleName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();

        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<RoleForm> list = (List<RoleForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public void defultSetting() {
        {
            String str = "Property Furnished,/getpropertyfurnishedlist.json,/editpropertyfurnished.json,/savePropertyfurnished.json,/deletepropertyfurnished.json,/getPropertyfurnishedFormcombolist.json,/getPropertyfurnishedFormFilterlist.json,/getPropertyfurnishedFormExportlist.json,Agent,/getagentlist.json,/editagent.json,/saveAgent.json,/deleteagent.json,/getAgentFormcombolist.json,/getAgentFormFilterlist.json,/getAgentFormExportlist.json,Role,/getrolelist.json,/editrole.json,/saveRole.json,/deleterole.json,/getRoleFormcombolist.json,/getRoleFormFilterlist.json,/getRoleFormExportlist.json,Occupation,/getoccupationlist.json,/editoccupation.json,/saveOccupation.json,/deleteoccupation.json,/getOccupationFormcombolist.json,/getOccupationFormFilterlist.json,/getOccupationFormExportlist.json,Gender,/getgenderlist.json,/editgender.json,/saveGender.json,/deletegender.json,/getGenderFormcombolist.json,/getGenderFormFilterlist.json,/getGenderFormExportlist.json,City,/getcitylist.json,/editcity.json,/saveCity.json,/deletecity.json,/getCityFormcombolist.json,/getCityFormFilterlist.json,/getCityFormExportlist.json,Property Type,/getpropertytypelist.json,/editpropertytype.json,/savePropertytype.json,/deletepropertytype.json,/getPropertytypeFormcombolist.json,/getPropertytypeFormFilterlist.json,/getPropertytypeFormExportlist.json,Property Bed Rooms,/getpropertylbedroomslist.json,/editpropertylbedrooms.json,/savePropertylbedrooms.json,/deletepropertylbedrooms.json,/getPropertylbedroomsFormcombolist.json,/getPropertylbedroomsFormFilterlist.json,/getPropertylbedroomsFormExportlist.json,Budget Rent,/getbudgetrentlist.json,/editbudgetrent.json,/saveBudgetrent.json,/deletebudgetrent.json,/getBudgetrentFormcombolist.json,/getBudgetrentFormFilterlist.json,/getBudgetrentFormExportlist.json,File,/getfilelist.json,/editfile.json,/saveFile.json,/deletefile.json,/getFileFormcombolist.json,/getFileFormFilterlist.json,/getFileFormExportlist.json,Property To be,/getpropertytobelist.json,/editpropertytobe.json,/savePropertytobe.json,/deletepropertytobe.json,/getPropertytobeFormcombolist.json,/getPropertytobeFormFilterlist.json,/getPropertytobeFormExportlist.json,Inquiry,/getinquirylist.json,/editinquiry.json,/saveInquiry.json,/deleteinquiry.json,/getInquiryFormcombolist.json,/getInquiryFormFilterlist.json,/getInquiryFormExportlist.json,Flat Amenities,/getflatamenitieslist.json,/editflatamenities.json,/saveFlatamenities.json,/deleteflatamenities.json,/getFlatamenitiesFormcombolist.json,/getFlatamenitiesFormFilterlist.json,/getFlatamenitiesFormExportlist.json,Property,/getpropertylist.json,/editproperty.json,/saveProperty.json,/deleteproperty.json,/getPropertyFormcombolist.json,/getPropertyFormFilterlist.json,/getPropertyFormExportlist.json,Mode of Operation,/getmodeoperationlist.json,/editmodeoperation.json,/saveModeoperation.json,/deletemodeoperation.json,/getModeoperationFormcombolist.json,/getModeoperationFormFilterlist.json,/getModeoperationFormExportlist.json,Data Recovery,/getdatarecoverylist.json,/editdatarecovery.json,/saveDatarecovery.json,/deletedatarecovery.json,/getDatarecoveryFormcombolist.json,/getDatarecoveryFormFilterlist.json,/getDatarecoveryFormExportlist.json,Society Amenities,/getsocietyamenitieslist.json,/editsocietyamenities.json,/saveSocietyamenities.json,/deletesocietyamenities.json,/getSocietyamenitiesFormcombolist.json,/getSocietyamenitiesFormFilterlist.json,/getSocietyamenitiesFormExportlist.json,Customer Type,/getcustomertypelist.json,/editcustomertype.json,/saveCustomertype.json,/deletecustomertype.json,/getCustomertypeFormcombolist.json,/getCustomertypeFormFilterlist.json,/getCustomertypeFormExportlist.json,Property Priority,/getpropertyprioritylist.json,/editpropertypriority.json,/savePropertypriority.json,/deletepropertypriority.json,/getPropertypriorityFormcombolist.json,/getPropertypriorityFormFilterlist.json,/getPropertypriorityFormExportlist.json,Role Permission,/getrolepermissionlist.json,/editrolepermission.json,/saveRolepermission.json,/deleterolepermission.json,/getRolepermissionFormcombolist.json,/getRolepermissionFormFilterlist.json,/getRolepermissionFormExportlist.json,Budget Sale,/getbudgetsalelist.json,/editbudgetsale.json,/saveBudgetsale.json,/deletebudgetsale.json,/getBudgetsaleFormcombolist.json,/getBudgetsaleFormFilterlist.json,/getBudgetsaleFormExportlist.json,Test,/gettestlist.json,/edittest.json,/saveTest.json,/deletetest.json,/getTestFormcombolist.json,/getTestFormFilterlist.json,/getTestFormExportlist.json,Property Locality,/getpropertylocalitylist.json,/editpropertylocality.json,/savePropertylocality.json,/deletepropertylocality.json,/getPropertylocalityFormcombolist.json,/getPropertylocalityFormFilterlist.json,/getPropertylocalityFormExportlist.json,Property Facing,/getpropertyfacinglist.json,/editpropertyfacing.json,/savePropertyfacing.json,/deletepropertyfacing.json,/getPropertyfacingFormcombolist.json,/getPropertyfacingFormFilterlist.json,/getPropertyfacingFormExportlist.json,User,/getuserlist.json,/edituser.json,/saveUser.json,/deleteuser.json,/getUserFormcombolist.json,/getUserFormFilterlist.json,/getUserFormExportlist.json,Operation,/getoperationlist.json,/editoperation.json,/saveOperation.json,/deleteoperation.json,/getOperationFormcombolist.json,/getOperationFormFilterlist.json,/getOperationFormExportlist.json,Customer,/getcustomerlist.json,/editcustomer.json,/saveCustomer.json,/deletecustomer.json,/getCustomerFormcombolist.json,/getCustomerFormFilterlist.json,/getCustomerFormExportlist.json";
            //     if (list.size() == 0) { 

            String temp[] = str.split(",");
            for (int i = 0; i < temp.length; i += 8) {
                Criteria cr = sessionfactory.getCurrentSession().createCriteria(OperationForm.class);
                List<OperationForm> list = (List<OperationForm>) cr.add(Restrictions.eq("name", temp[i])).list();
                if (list.size() == 0) {

                    OperationForm op = new OperationForm();
                    op.setFunname("Menu");
                    op.setUrl("#");
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Grid");
                    op.setUrl(temp[i + 1]);
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Filter");
                    op.setUrl(temp[i + 6]);
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Export");
                    op.setUrl(temp[i + 7]);
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Add");
                    op.setUrl(temp[i + 3]);
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Edit");
                    op.setUrl(temp[i + 2]);
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Delete");
                    op.setUrl(temp[i + 4]);
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Combo");
                    op.setUrl(temp[i + 5]);
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

                    op = new OperationForm();
                    op.setFunname("Report");
                    op.setUrl("#");
                    op.setName(temp[i]);
                    op.setActive("1");
                    op.setCreateddate(new Date());
                    op.setModifyddate(new Date());
                    sessionfactory.getCurrentSession().save(op);

//                    op.setUrladd(temp[i + 3]);
//                    op.setUrlcombo(temp[i + 5]);
//                    op.setUrledit(temp[i + 2]);
//                    op.setUrlgrid(temp[i + 1]);
//                    op.setUrldelete(temp[i + 4]);
//                    
                }
            }
     
        }
        {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(RoleForm.class);
            List<RoleForm> list = (List<RoleForm>) cr.add(Restrictions.eq("name", "admin")).list();
            if (list.size() == 0) {
                RoleForm rm = new RoleForm();
                rm.setName("admin");
                rm.setActive("1");
                sessionfactory.getCurrentSession().save(rm);
                updateRoleTable(rm);
            } else {
                updateRole(list.get(0));
            }

        }
        {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(UserForm.class);
            List<UserForm> list = (List<UserForm>) cr.add(Restrictions.eq("email", "admin@admin.com")).list();
            if (list.size() == 0) {
                UserForm rm = new UserForm();
                rm.setCreateddate(new Date());//Active(active);Name("admin");
                rm.setEmail("admin@admin.com");
                rm.setPassword("12");
                rm.setRoleid((RoleForm) sessionfactory.getCurrentSession().createCriteria(RoleForm.class).add(Restrictions.eq("name", "admin")).list().get(0));
                rm.setActive("1");
                //rm.setUserid(0);
                sessionfactory.getCurrentSession().save(rm);
                //   updateRoleTable(rm);
            }

        }

    }

    public void updateRoleTable(RoleForm r) {
        {

            Criteria cr = sessionfactory.getCurrentSession().createCriteria(OperationForm.class);
            List<OperationForm> list = (List<OperationForm>) cr.addOrder(Order.asc("name")).list();

            for (OperationForm operationForm : list) {
                Criteria cr1 = sessionfactory.getCurrentSession().createCriteria(RolepermissionForm.class);
                cr1 = cr1.add(Restrictions.eq("operationid.operationid", operationForm.getOperationid()));
                cr1 = cr1.add(Restrictions.eq("roleid.roleid", r.roleid));
                if (cr1.list().size() == 0) {
                    RolepermissionForm rm = new RolepermissionForm();
                    rm.setOperationid(operationForm);
                    rm.setPfunction(true);
                    rm.setActive("1");
                    rm.setCreateddate(new Date());
                    rm.setModifyddate(new Date());
                    rm.setRoleid(r);
                    sessionfactory.getCurrentSession().save(rm);
                }

            }
        }
    }

    @Override
    public RoleForm editRole(Integer id) {
        RoleForm roleForm = (RoleForm) sessionfactory.getCurrentSession().get(RoleForm.class, id);

        return roleForm;
    }

    @Override
    public void updateRole(RoleForm roleForm) {
        setFK(roleForm);
        sessionfactory.getCurrentSession().update(roleForm);

        updateRoleTable(roleForm);
    }

    @Override
    public void deleteRole(Integer id) {
        RoleForm roleForm = (RoleForm) sessionfactory.getCurrentSession().load(RoleForm.class, id);

        if (roleForm != null) {
            roleForm.setActive("0");
            sessionfactory.getCurrentSession().update(roleForm);
        }

    }

    @Override
    public List<SelectCombo> getRoleComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select role." + request.getParameter("col") + " , count(*) as cnt from Role role "
//                    + "where role.active='1' "
//                    + "group by role." + request.getParameter("col") + " order by role." + request.getParameter("col") + " ").list();
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
////            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select role.roleid,role.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
////                    + "left join  " + table.toLowerCase() + ".roleid  role where role.roleid = " + table.toLowerCase() + ".roleid.roleid and  role.active='1' group by role.roleid,role.name  "
////                    + "order by cnt desc").list();
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select role.roleid,role.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "  join  " + table.toLowerCase() + ".roleid  role where  role.active='1' group by role.roleid,role.name  "
//                    + "order by cnt desc").list();
//            for (int i = 0; i < l.size(); i++) {
//                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
//                        (String) l.get(i)[1],
//                        ((Long) l.get(i)[2]).intValue()));
//            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(RoleForm.class);

            List<RoleForm> list = (List<RoleForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (RoleForm roleForm : list) {
                SelectCombo obj = (new SelectCombo(roleForm.getRoleid(), roleForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(roleForm.getRoleid(), roleForm.getName()));
            }
        }
        return al;
    }

    public void setFK(RoleForm roleForm) {

    }
}
