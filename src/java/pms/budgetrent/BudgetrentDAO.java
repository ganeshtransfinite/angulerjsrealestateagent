/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.budgetrent;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface BudgetrentDAO {
 
    void addBudgetrent(BudgetrentForm budgetrentForm);
    public BudgetrentForm editBudgetrent(Integer id);
    void updateBudgetrent(BudgetrentForm budgetrentForm);
    void deleteBudgetrent(Integer id);
    TableForm getBudgetrentList(TableForm tableFrom);
 
       public List<SelectCombo> getBudgetrentComboList(HttpServletRequest request);
}
