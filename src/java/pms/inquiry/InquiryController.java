package pms.inquiry;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jr.ob.JSON;
import admin.filter.SelectCombo;
import admin.filter.TableForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
/**
 *
 * @inquiry user
 */
@Controller
public class InquiryController {
     @Autowired(required = true)
    InquiryService inquiryService;
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveInquiry.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    InquiryForm addInquiry(@RequestBody InquiryForm inquiryForm) {
        if (inquiryForm != null) {
            try {
                    
inquiryForm.setActive("1"); 
inquiryForm.setCreateddate(new Date()); 
inquiryForm.setModifyddate(new Date()); 
 if (inquiryForm.getInquiryid() > 0) {
                  
inquiryService.updateInquiry(inquiryForm);
                }else{
                    inquiryService.addInquiry(inquiryForm);
                }
                    
                
                //              model.addAttribute("msg", "Succesfully Inquiry  Saved.");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    
        return inquiryForm;
    }
    
    @RequestMapping(value = "/editinquiry.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    InquiryForm editInquirydata(@RequestBody final InquiryForm inquiryForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        InquiryForm inquiryForm1 = inquiryService.editInquiry(inquiryForm.getInquiryid());
        return inquiryForm1;
    }
    @RequestMapping(value = "/getinquirylist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getInquiryList(@RequestBody final TableForm inquiryForm) throws Exception {
        inquiryService.getInquiryList(inquiryForm);
        return inquiryForm;
    }
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteinquiry.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteInquiry(@RequestBody final String inquiryForm) throws Exception {
       String str[]=inquiryForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    inquiryService.deleteInquiry(Integer.parseInt(str[i]));
                }
                
                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }
    
        return "ok";
    }
 @RequestMapping(value = "/getInquiryFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getInquirycomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> inquirylist = inquiryService.getInquiryComboList( request);
        return inquirylist;
    }
}
 
