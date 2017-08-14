package pms.occupation;
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
public class OccupationServiceImpl implements OccupationService {
    @Autowired(required = true)
    OccupationDAO occupationDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOccupation(OccupationForm occupationForm) {
        occupationDAO.addOccupation(occupationForm);
    }
    @Override
    @Transactional
    public TableForm getOccupationList(TableForm tableform) {
        return occupationDAO.getOccupationList(tableform);
    }
    @Override
    @Transactional
    public OccupationForm editOccupation(Integer id) {
        return occupationDAO.editOccupation(id);
    }
    @Override
    @Transactional
    public void updateOccupation(OccupationForm occupationForm) {
        occupationDAO.updateOccupation(occupationForm);
    }
    @Override
    @Transactional
    public void deleteOccupation(Integer id) {
        occupationDAO.deleteOccupation(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getOccupationComboList(HttpServletRequest request) {
        return occupationDAO.getOccupationComboList(request);
    }
}
