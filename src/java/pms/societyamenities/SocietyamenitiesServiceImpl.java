package pms.societyamenities;
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
public class SocietyamenitiesServiceImpl implements SocietyamenitiesService {
    @Autowired(required = true)
    SocietyamenitiesDAO societyamenitiesDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSocietyamenities(SocietyamenitiesForm societyamenitiesForm) {
        societyamenitiesDAO.addSocietyamenities(societyamenitiesForm);
    }
    @Override
    @Transactional
    public TableForm getSocietyamenitiesList(TableForm tableform) {
        return societyamenitiesDAO.getSocietyamenitiesList(tableform);
    }
    @Override
    @Transactional
    public SocietyamenitiesForm editSocietyamenities(Integer id) {
        return societyamenitiesDAO.editSocietyamenities(id);
    }
    @Override
    @Transactional
    public void updateSocietyamenities(SocietyamenitiesForm societyamenitiesForm) {
        societyamenitiesDAO.updateSocietyamenities(societyamenitiesForm);
    }
    @Override
    @Transactional
    public void deleteSocietyamenities(Integer id) {
        societyamenitiesDAO.deleteSocietyamenities(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getSocietyamenitiesComboList(HttpServletRequest request) {
        return societyamenitiesDAO.getSocietyamenitiesComboList(request);
    }
}
