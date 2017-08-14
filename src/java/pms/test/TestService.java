/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.test;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Test Vision
 */
public interface TestService {
    
     void addTest(TestForm testForm);
    void updateTest(TestForm testForm);
    void deleteTest(Integer id);
    TableForm getTestList(TableForm taleform);
 
    public TestForm editTest(Integer id);
    
        public List<SelectCombo> getTestComboList(HttpServletRequest request);
}
