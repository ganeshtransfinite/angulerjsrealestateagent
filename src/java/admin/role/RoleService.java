/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.role;

import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Role Vision
 */
public interface RoleService {

    void addRole(RoleForm roleForm);

    void updateRole(RoleForm roleForm);

    void deleteRole(Integer id);

    TableForm getRoleList(TableForm taleform);

    public RoleForm editRole(Integer id);

    public List<SelectCombo> getRoleComboList(HttpServletRequest request);
      public void defultSetting();
}
