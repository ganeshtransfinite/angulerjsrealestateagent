/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.agent;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface AgentDAO {
 
    void addAgent(AgentForm agentForm);
    public AgentForm editAgent(Integer id);
    void updateAgent(AgentForm agentForm);
    void deleteAgent(Integer id);
    TableForm getAgentList(TableForm tableFrom);
 
       public List<SelectCombo> getAgentComboList(HttpServletRequest request);
}
