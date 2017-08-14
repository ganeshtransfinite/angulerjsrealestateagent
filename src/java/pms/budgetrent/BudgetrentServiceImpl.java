package pms.budgetrent;
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
public class BudgetrentServiceImpl implements BudgetrentService {
    @Autowired(required = true)
    BudgetrentDAO budgetrentDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBudgetrent(BudgetrentForm budgetrentForm) {
        budgetrentDAO.addBudgetrent(budgetrentForm);
    }
    @Override
    @Transactional
    public TableForm getBudgetrentList(TableForm tableform) {
        return budgetrentDAO.getBudgetrentList(tableform);
    }
    @Override
    @Transactional
    public BudgetrentForm editBudgetrent(Integer id) {
        return budgetrentDAO.editBudgetrent(id);
    }
    @Override
    @Transactional
    public void updateBudgetrent(BudgetrentForm budgetrentForm) {
        budgetrentDAO.updateBudgetrent(budgetrentForm);
    }
    @Override
    @Transactional
    public void deleteBudgetrent(Integer id) {
        budgetrentDAO.deleteBudgetrent(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getBudgetrentComboList(HttpServletRequest request) {
        return budgetrentDAO.getBudgetrentComboList(request);
    }
}
