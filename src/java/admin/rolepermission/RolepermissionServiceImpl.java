package admin.rolepermission;
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
public class RolepermissionServiceImpl implements RolepermissionService {
    @Autowired(required = true)
    RolepermissionDAO rolepermissionDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addRolepermission(RolepermissionForm rolepermissionForm) {
        rolepermissionDAO.addRolepermission(rolepermissionForm);
    }
    @Override
    @Transactional
    public TableForm getRolepermissionList(TableForm tableform) {
        return rolepermissionDAO.getRolepermissionList(tableform);
    }
    @Override
    @Transactional
    public RolepermissionForm editRolepermission(Integer id) {
        return rolepermissionDAO.editRolepermission(id);
    }
    @Override
    @Transactional
    public void updateRolepermission(RolepermissionForm rolepermissionForm) {
        rolepermissionDAO.updateRolepermission(rolepermissionForm);
    }
    @Override
    @Transactional
    public void deleteRolepermission(Integer id) {
        rolepermissionDAO.deleteRolepermission(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getRolepermissionComboList(HttpServletRequest request) {
        return rolepermissionDAO.getRolepermissionComboList(request);
    }
     @Override
    @Transactional
     public void updateRolepermission(int id, boolean val, String col){
            rolepermissionDAO.updateRolepermission(id,val,col);
     }
}
