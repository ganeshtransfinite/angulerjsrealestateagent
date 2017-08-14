package pms.agent;
import admin.filter.SelectCombo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import admin.filter.TableForm;
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AgentServiceImpl implements AgentService {
    @Autowired(required = true)
    AgentDAO agentDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAgent(AgentForm agentForm) {
        agentDAO.addAgent(agentForm);
    }
    @Override
    @Transactional
    public TableForm getAgentList(TableForm tableform) {
        return agentDAO.getAgentList(tableform);
    }
    @Override
    @Transactional
    public AgentForm editAgent(Integer id) {
        return agentDAO.editAgent(id);
    }
    @Override
    @Transactional
    public void updateAgent(AgentForm agentForm) {
        agentDAO.updateAgent(agentForm);
    }
    @Override
    @Transactional
    public void deleteAgent(Integer id) {
        agentDAO.deleteAgent(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getAgentComboList(HttpServletRequest request) {
        return agentDAO.getAgentComboList(request);
    }
}
