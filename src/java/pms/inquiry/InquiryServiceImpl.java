package pms.inquiry;
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
public class InquiryServiceImpl implements InquiryService {
    @Autowired(required = true)
    InquiryDAO inquiryDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addInquiry(InquiryForm inquiryForm) {
        inquiryDAO.addInquiry(inquiryForm);
    }
    @Override
    @Transactional
    public TableForm getInquiryList(TableForm tableform) {
        return inquiryDAO.getInquiryList(tableform);
    }
    @Override
    @Transactional
    public InquiryForm editInquiry(Integer id) {
        return inquiryDAO.editInquiry(id);
    }
    @Override
    @Transactional
    public void updateInquiry(InquiryForm inquiryForm) {
        inquiryDAO.updateInquiry(inquiryForm);
    }
    @Override
    @Transactional
    public void deleteInquiry(Integer id) {
        inquiryDAO.deleteInquiry(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getInquiryComboList(HttpServletRequest request) {
        return inquiryDAO.getInquiryComboList(request);
    }
}
