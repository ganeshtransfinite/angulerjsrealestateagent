/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.budgetsale;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface BudgetsaleDAO {
 
    void addBudgetsale(BudgetsaleForm budgetsaleForm);
    public BudgetsaleForm editBudgetsale(Integer id);
    void updateBudgetsale(BudgetsaleForm budgetsaleForm);
    void deleteBudgetsale(Integer id);
    TableForm getBudgetsaleList(TableForm tableFrom);
 
       public List<SelectCombo> getBudgetsaleComboList(HttpServletRequest request);
}
