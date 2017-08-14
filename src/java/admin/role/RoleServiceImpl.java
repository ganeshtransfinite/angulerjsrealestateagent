package admin.role;

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
public class RoleServiceImpl implements RoleService {

    @Autowired(required = true)
    RoleDAO roleDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addRole(RoleForm roleForm) {
        roleDAO.addRole(roleForm);
    }

    @Override
    @Transactional
    public TableForm getRoleList(TableForm tableform) {
        return roleDAO.getRoleList(tableform);
    }

    @Override
    @Transactional
    public RoleForm editRole(Integer id) {
        return roleDAO.editRole(id);
    }

    @Override
    @Transactional
    public void updateRole(RoleForm roleForm) {
        roleDAO.updateRole(roleForm);
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) {
        roleDAO.deleteRole(id);
    }

    @Override
    @Transactional
    public List<SelectCombo> getRoleComboList(HttpServletRequest request) {
        return roleDAO.getRoleComboList(request);
    }

    @Override
    @Transactional
    public void defultSetting() {
          roleDAO.defultSetting();
    }
}
