package pms.budgetsale;
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
public class BudgetsaleServiceImpl implements BudgetsaleService {
    @Autowired(required = true)
    BudgetsaleDAO budgetsaleDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBudgetsale(BudgetsaleForm budgetsaleForm) {
        budgetsaleDAO.addBudgetsale(budgetsaleForm);
    }
    @Override
    @Transactional
    public TableForm getBudgetsaleList(TableForm tableform) {
        return budgetsaleDAO.getBudgetsaleList(tableform);
    }
    @Override
    @Transactional
    public BudgetsaleForm editBudgetsale(Integer id) {
        return budgetsaleDAO.editBudgetsale(id);
    }
    @Override
    @Transactional
    public void updateBudgetsale(BudgetsaleForm budgetsaleForm) {
        budgetsaleDAO.updateBudgetsale(budgetsaleForm);
    }
    @Override
    @Transactional
    public void deleteBudgetsale(Integer id) {
        budgetsaleDAO.deleteBudgetsale(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getBudgetsaleComboList(HttpServletRequest request) {
        return budgetsaleDAO.getBudgetsaleComboList(request);
    }
}
