/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.inquiry;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Inquiry Vision
 */
public interface InquiryService {
    
     void addInquiry(InquiryForm inquiryForm);
    void updateInquiry(InquiryForm inquiryForm);
    void deleteInquiry(Integer id);
    TableForm getInquiryList(TableForm taleform);
 
    public InquiryForm editInquiry(Integer id);
    
        public List<SelectCombo> getInquiryComboList(HttpServletRequest request);
}
