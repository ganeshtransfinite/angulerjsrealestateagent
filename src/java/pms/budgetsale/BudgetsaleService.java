/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.budgetsale;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Budgetsale Vision
 */
public interface BudgetsaleService {
    
     void addBudgetsale(BudgetsaleForm budgetsaleForm);
    void updateBudgetsale(BudgetsaleForm budgetsaleForm);
    void deleteBudgetsale(Integer id);
    TableForm getBudgetsaleList(TableForm taleform);
 
    public BudgetsaleForm editBudgetsale(Integer id);
    
        public List<SelectCombo> getBudgetsaleComboList(HttpServletRequest request);
}
