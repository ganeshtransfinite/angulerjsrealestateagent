package pms.test;
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
public class TestServiceImpl implements TestService {
    @Autowired(required = true)
    TestDAO testDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addTest(TestForm testForm) {
        testDAO.addTest(testForm);
    }
    @Override
    @Transactional
    public TableForm getTestList(TableForm tableform) {
        return testDAO.getTestList(tableform);
    }
    @Override
    @Transactional
    public TestForm editTest(Integer id) {
        return testDAO.editTest(id);
    }
    @Override
    @Transactional
    public void updateTest(TestForm testForm) {
        testDAO.updateTest(testForm);
    }
    @Override
    @Transactional
    public void deleteTest(Integer id) {
        testDAO.deleteTest(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getTestComboList(HttpServletRequest request) {
        return testDAO.getTestComboList(request);
    }
}
