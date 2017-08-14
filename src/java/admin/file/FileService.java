/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.file;
 
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @File Vision
 */
public interface FileService {
    
     void addFile(FileForm fileForm);
    void updateFile(FileForm fileForm);
    void deleteFile(Integer id);
    TableForm getFileList(TableForm taleform);
 
    public FileForm editFile(Integer id);
    public byte[] getIcon(byte[] imagedata);
        public List<SelectCombo> getFileComboList(HttpServletRequest request);
}
