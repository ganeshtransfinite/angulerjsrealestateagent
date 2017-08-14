/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pms.inquiry;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface InquiryDAO {
 
    void addInquiry(InquiryForm inquiryForm);
    public InquiryForm editInquiry(Integer id);
    void updateInquiry(InquiryForm inquiryForm);
    void deleteInquiry(Integer id);
    TableForm getInquiryList(TableForm tableFrom);
 
       public List<SelectCombo> getInquiryComboList(HttpServletRequest request);
}
