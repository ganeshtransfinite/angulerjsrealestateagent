/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.role;

import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;

/**
 *
 * @author Vision
 */
public interface RoleDAO {
  public void defultSetting();
    void addRole(RoleForm roleForm);

    public RoleForm editRole(Integer id);

    void updateRole(RoleForm roleForm);

    void deleteRole(Integer id);

    TableForm getRoleList(TableForm tableFrom);

    public List<SelectCombo> getRoleComboList(HttpServletRequest request);
}
