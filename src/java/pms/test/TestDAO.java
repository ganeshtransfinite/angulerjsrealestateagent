/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.test;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface TestDAO {
 
    void addTest(TestForm testForm);
    public TestForm editTest(Integer id);
    void updateTest(TestForm testForm);
    void deleteTest(Integer id);
    TableForm getTestList(TableForm tableFrom);
 
       public List<SelectCombo> getTestComboList(HttpServletRequest request);
}
