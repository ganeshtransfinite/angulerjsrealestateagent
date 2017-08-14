package admin.file;

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
 * @file user
 */
@Controller
public class FileController {

    @Autowired(required = true)
    FileService fileService;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveFile.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    FileForm addFile(@RequestBody FileForm fileForm) {
        if (fileForm != null) {
            try {

                fileForm.setActive("1");
                fileForm.setCreateddate(new Date());
                fileForm.setModifyddate(new Date());
                if (fileForm.getFileid() > 0) {

                    fileService.updateFile(fileForm);
                } else {
                    fileService.addFile(fileForm);
                }

                //              model.addAttribute("msg", "Succesfully File  Saved.");
            } catch (Exception e) {
                System.out.println("message" + e.getMessage());
            }
        }

        return fileForm;
    }

    @RequestMapping(value = "/editfile.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    FileForm editFiledata(@RequestBody final FileForm fileForm, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        FileForm fileForm1 = fileService.editFile(fileForm.getFileid());
        return fileForm1;
    }

    @RequestMapping(value = "/getfilelist.json", headers = "Accept=*/*",
            method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    TableForm getFileList(@RequestBody final TableForm fileForm) throws Exception {
        fileService.getFileList(fileForm);
        return fileForm;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deletefile.json", headers = "Accept=*/*",
            method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String deleteFile(@RequestBody final String fileForm) throws Exception {
        String str[] = fileForm.split(",");
        if (str.length > 0) {
            try {
                for (int i = 0; i < str.length; i++) {
                    fileService.deleteFile(Integer.parseInt(str[i]));
                }

                //      model.addAttribute("msg", "Succesfully Student  Deleted.");
            } catch (Exception e) {
                return "ERROR";
                //System.out.println("" + e.getMessage());
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/getFileFormcombolist.json", headers = "Accept=*/*",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SelectCombo> getFilecomboList(HttpServletRequest request) throws Exception {
        List<SelectCombo> filelist = fileService.getFileComboList(request);
        return filelist;
    }
}
