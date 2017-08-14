package admin.file;
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
public class FileServiceImpl implements FileService {
    @Autowired(required = true)
    FileDAO fileDAO;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addFile(FileForm fileForm) {
        fileDAO.addFile(fileForm);
    }
    @Override
    @Transactional
    public TableForm getFileList(TableForm tableform) {
        return fileDAO.getFileList(tableform);
    }
    @Override
    @Transactional
    public FileForm editFile(Integer id) {
        return fileDAO.editFile(id);
    }
    @Override
    @Transactional
    public void updateFile(FileForm fileForm) {
        fileDAO.updateFile(fileForm);
    }
    @Override
    @Transactional
    public void deleteFile(Integer id) {
        fileDAO.deleteFile(id);
    }
     
    @Override
    @Transactional
    public List<SelectCombo> getFileComboList(HttpServletRequest request) {
        return fileDAO.getFileComboList(request);
    }
     @Override
    @Transactional
    public byte[] getIcon(byte[] imagedata){
        return fileDAO.getIcon(imagedata);
    }
}
