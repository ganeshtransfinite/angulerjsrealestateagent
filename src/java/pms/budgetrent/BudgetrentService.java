/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.budgetrent;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Budgetrent Vision
 */
public interface BudgetrentService {
    
     void addBudgetrent(BudgetrentForm budgetrentForm);
    void updateBudgetrent(BudgetrentForm budgetrentForm);
    void deleteBudgetrent(Integer id);
    TableForm getBudgetrentList(TableForm taleform);
 
    public BudgetrentForm editBudgetrent(Integer id);
    
        public List<SelectCombo> getBudgetrentComboList(HttpServletRequest request);
}
