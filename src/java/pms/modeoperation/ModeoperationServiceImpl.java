package pms.modeoperation;
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
public class ModeoperationServiceImpl implements ModeoperationService {
    @Autowired(required = true)
    ModeoperationDAO modeoperationDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addModeoperation(ModeoperationForm modeoperationForm) {
        modeoperationDAO.addModeoperation(modeoperationForm);
    }
    @Override
    @Transactional
    public TableForm getModeoperationList(TableForm tableform) {
        return modeoperationDAO.getModeoperationList(tableform);
    }
    @Override
    @Transactional
    public ModeoperationForm editModeoperation(Integer id) {
        return modeoperationDAO.editModeoperation(id);
    }
    @Override
    @Transactional
    public void updateModeoperation(ModeoperationForm modeoperationForm) {
        modeoperationDAO.updateModeoperation(modeoperationForm);
    }
    @Override
    @Transactional
    public void deleteModeoperation(Integer id) {
        modeoperationDAO.deleteModeoperation(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getModeoperationComboList(HttpServletRequest request) {
        return modeoperationDAO.getModeoperationComboList(request);
    }
}
