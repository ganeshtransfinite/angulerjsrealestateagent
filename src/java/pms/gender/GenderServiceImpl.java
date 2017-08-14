package pms.gender;
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
public class GenderServiceImpl implements GenderService {
    @Autowired(required = true)
    GenderDAO genderDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addGender(GenderForm genderForm) {
        genderDAO.addGender(genderForm);
    }
    @Override
    @Transactional
    public TableForm getGenderList(TableForm tableform) {
        return genderDAO.getGenderList(tableform);
    }
    @Override
    @Transactional
    public GenderForm editGender(Integer id) {
        return genderDAO.editGender(id);
    }
    @Override
    @Transactional
    public void updateGender(GenderForm genderForm) {
        genderDAO.updateGender(genderForm);
    }
    @Override
    @Transactional
    public void deleteGender(Integer id) {
        genderDAO.deleteGender(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getGenderComboList(HttpServletRequest request) {
        return genderDAO.getGenderComboList(request);
    }
}
