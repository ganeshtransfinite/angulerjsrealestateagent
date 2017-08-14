package pms.flatamenities;
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
public class FlatamenitiesServiceImpl implements FlatamenitiesService {
    @Autowired(required = true)
    FlatamenitiesDAO flatamenitiesDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addFlatamenities(FlatamenitiesForm flatamenitiesForm) {
        flatamenitiesDAO.addFlatamenities(flatamenitiesForm);
    }
    @Override
    @Transactional
    public TableForm getFlatamenitiesList(TableForm tableform) {
        return flatamenitiesDAO.getFlatamenitiesList(tableform);
    }
    @Override
    @Transactional
    public FlatamenitiesForm editFlatamenities(Integer id) {
        return flatamenitiesDAO.editFlatamenities(id);
    }
    @Override
    @Transactional
    public void updateFlatamenities(FlatamenitiesForm flatamenitiesForm) {
        flatamenitiesDAO.updateFlatamenities(flatamenitiesForm);
    }
    @Override
    @Transactional
    public void deleteFlatamenities(Integer id) {
        flatamenitiesDAO.deleteFlatamenities(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getFlatamenitiesComboList(HttpServletRequest request) {
        return flatamenitiesDAO.getFlatamenitiesComboList(request);
    }
}
