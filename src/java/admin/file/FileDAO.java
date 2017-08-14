/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.file;
import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
/**
 *
 * @author Vision
 */
public interface FileDAO {
 
    void addFile(FileForm fileForm);
    public FileForm editFile(Integer id);
    void updateFile(FileForm fileForm);
    void deleteFile(Integer id);
    TableForm getFileList(TableForm tableFrom);
 public byte[] getIcon(byte[] imagedata);
       public List<SelectCombo> getFileComboList(HttpServletRequest request);
}
