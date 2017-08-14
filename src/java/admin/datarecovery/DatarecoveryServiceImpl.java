package admin.datarecovery;

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
public class DatarecoveryServiceImpl implements DatarecoveryService {

    @Autowired(required = true)
    DatarecoveryDAO datarecoveryDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addDatarecovery(DatarecoveryForm datarecoveryForm) {
        datarecoveryDAO.addDatarecovery(datarecoveryForm);
    }

    @Override
    @Transactional
    public TableForm getDatarecoveryList(TableForm tableform) {
        return datarecoveryDAO.getDatarecoveryList(tableform);
    }

    @Override
    @Transactional
    public DatarecoveryForm editDatarecovery(Integer id) {
        return datarecoveryDAO.editDatarecovery(id);
    }

    @Override
    @Transactional
    public void updateDatarecovery(DatarecoveryForm datarecoveryForm) {
        datarecoveryDAO.updateDatarecovery(datarecoveryForm);
    }

    @Override
    @Transactional
    public void deleteDatarecovery(Integer id) {
        datarecoveryDAO.deleteDatarecovery(id);
    }

    @Override
    @Transactional
    public List<SelectCombo> getDatarecoveryComboList(HttpServletRequest request) {
        return datarecoveryDAO.getDatarecoveryComboList(request);
    }
}
