package pms.agent;

import pms.agent.AgentForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import pms.agent.AgentForm;
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
import pms.modeoperation.ModeoperationForm;
import pms.propertylocality.PropertylocalityForm;
import admin.user.UserForm;

/**
 *
 * @agent Vision
 */
@Repository
public class AgentDAOImpl implements AgentDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addAgent(AgentForm agentForm) {
        setFK(agentForm);
        sessionfactory.getCurrentSession().save(agentForm);
    }

    @Override
    public TableForm getAgentList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(AgentForm.class
        );

//        if (tableForm.getFilter().indexOf("cityid") != -1 || tableForm.getOrder().indexOf("cityid") != -1) {
//            cr = cr.createAlias("cityid", "cityid", Criteria.LEFT_JOIN);
//        }
       // if (tableForm.getFilter().indexOf("propertylocalityid") != -1) {
            cr = cr.createAlias("propertylocalityid", "propertylocalityid");
        //}
//        if (tableForm.getFilter().indexOf("modeoperationid") != -1) {
            cr = cr.createAlias("modeoperationid", "modeoperationid");
//        }
        if (tableForm.getFilter().indexOf("userid") != -1 || tableForm.getOrder().indexOf("userid") != -1) {
            cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        }
      
        cr = CommonUtil.getFilterData(cr, tableForm);

        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("name", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("mobile", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("email", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("companyname", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("address", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("agentid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(agentName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        cr = cr.add(Restrictions.ilike("active", "1"));
        cr = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<AgentForm> list = (List<AgentForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        List<AgentForm> listRet = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            AgentForm obj = new AgentForm(list.get(i));

            listRet.add(obj);
        }
        tableForm.setItems(listRet);
        return tableForm;
    }

    @Override
    public AgentForm editAgent(Integer id) {
        AgentForm agentForm = (AgentForm) sessionfactory.getCurrentSession().get(AgentForm.class, id);

        if (agentForm.getCityid() != null) {
            agentForm.setSelectcityid(agentForm.getCityid().getCityid());
        }
        if (agentForm.getPropertylocalityid() != null) {
            Object[] obj = agentForm.getPropertylocalityid().toArray();
            agentForm.setSelectpropertylocalityid(new int[agentForm.getPropertylocalityid().size()]);
            for (int i = 0; i < agentForm.getPropertylocalityid().size(); i++) {
                agentForm.getSelectpropertylocalityid()[i] = ((PropertylocalityForm) obj[i]).getPropertylocalityid();
            }
        }
        if (agentForm.getModeoperationid() != null) {
            Object[] obj = agentForm.getModeoperationid().toArray();
            agentForm.setSelectmodeoperationid(new int[agentForm.getModeoperationid().size()]);
            for (int i = 0; i < agentForm.getModeoperationid().size(); i++) {
                agentForm.getSelectmodeoperationid()[i] = ((ModeoperationForm) obj[i]).getModeoperationid();
            }
        }
      
        return agentForm;
    }

    @Override
    public void updateAgent(AgentForm agentForm) {
        setFK(agentForm);
        sessionfactory.getCurrentSession().update(agentForm);
    }

    @Override
    public void deleteAgent(Integer id) {
        AgentForm agentForm = (AgentForm) sessionfactory.getCurrentSession().load(AgentForm.class, id);

        if (agentForm != null) {
            agentForm.setActive("0");
            sessionfactory.getCurrentSession().update(agentForm);
        }

    }

    @Override
    public List<SelectCombo> getAgentComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();
        if (request.getParameter("col") != null) {
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select agent." + request.getParameter("col") + " , count(*) as cnt from Agent agent "
                    + "where agent.active='1' "
                    + "group by agent." + request.getParameter("col") + " order by agent." + request.getParameter("col") + " ").list();
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
//            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select agent.agentid,agent.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
//                    + "left join  " + table.toLowerCase() + ".agentid  agent where agent.agentid = " + table.toLowerCase() + ".agentid.agentid and  agent.active='1' group by agent.agentid,agent.name  "
//                    + "order by cnt ").list();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select agent.agentid,agent.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "  join  " + table.toLowerCase() + ".agentid  agent where  agent.active='1' group by agent.agentid,agent.name  "
                    + "order by cnt ").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1],
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(AgentForm.class);

            if (request.getParameter("cityid") != null && !request.getParameter("cityid").equals("undefined") && !request.getParameter("cityid").equals("0")) {
                cr = cr.add(Restrictions.eq("cityid.cityid", Integer.parseInt(request.getParameter("cityid"))));
            }
            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
            }
            List<AgentForm> list = (List<AgentForm>) cr.addOrder(Order.asc("name")).add(Restrictions.ilike("active", "1")).list();

            for (AgentForm agentForm : list) {
                SelectCombo obj = (new SelectCombo(agentForm.getAgentid(), agentForm.getName()));

                al.add(obj);
//            al.add(new SelectCombo(agentForm.getAgentid(), agentForm.getName()));
            }
        }
        return al;
    }

    public void setFK(AgentForm agentForm) {

        if (agentForm.getSelectcityid() > 0) {
            agentForm.setCityid((CityForm) sessionfactory.getCurrentSession().get(CityForm.class, agentForm.getSelectcityid()));
        }
        if (agentForm.getSelectpropertylocalityid() != null) {
            agentForm.setPropertylocalityid(new ArrayList());
            for (int i = 0; i < agentForm.getSelectpropertylocalityid().length; i++) {
                agentForm.getPropertylocalityid().add((PropertylocalityForm) sessionfactory.getCurrentSession().get(PropertylocalityForm.class, agentForm.getSelectpropertylocalityid()[i]));
            }
        }
        if (agentForm.getSelectmodeoperationid() != null) {
            agentForm.setModeoperationid(new ArrayList());
            for (int i = 0; i < agentForm.getSelectmodeoperationid().length; i++) {
                agentForm.getModeoperationid().add((ModeoperationForm) sessionfactory.getCurrentSession().get(ModeoperationForm.class, agentForm.getSelectmodeoperationid()[i]));
            }
        }

        if (agentForm.getSelectuserid() > 0) {
            agentForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, agentForm.getSelectuserid()));
        }
    
    }
}
