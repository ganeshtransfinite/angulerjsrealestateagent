/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.agent;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Agent Vision
 */
public interface AgentService {
    
     void addAgent(AgentForm agentForm);
    void updateAgent(AgentForm agentForm);
    void deleteAgent(Integer id);
    TableForm getAgentList(TableForm taleform);
 
    public AgentForm editAgent(Integer id);
    
        public List<SelectCombo> getAgentComboList(HttpServletRequest request);
}
