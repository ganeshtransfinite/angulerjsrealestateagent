package admin.user;

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
public class UserServiceImpl implements UserService {

    @Autowired(required = true)
    UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(UserForm userForm) {
        userDAO.addUser(userForm);
    }

    @Override
    @Transactional
    public TableForm getUserList(TableForm tableform) {
        return userDAO.getUserList(tableform);
    }

    @Override
    @Transactional
    public UserForm editUser(Integer id) {
        return userDAO.editUser(id);
    }

    @Override
    @Transactional
    public String updateUser(UserForm userForm) {
       return userDAO.updateUser(userForm);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public List<SelectCombo> getUserComboList(HttpServletRequest request) {
        return userDAO.getUserComboList(request);
    }
      @Override
    @Transactional
    public boolean checkExist(String col, String val, int id){
         return userDAO.checkExist(col, val, id);
    }
}
