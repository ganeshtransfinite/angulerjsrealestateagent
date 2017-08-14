/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.user;

import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @User Vision
 */
public interface UserService {

    void addUser(UserForm userForm);

    String updateUser(UserForm userForm);

    void deleteUser(Integer id);

    TableForm getUserList(TableForm taleform);

    public boolean checkExist(String col, String val, int id);

    public UserForm editUser(Integer id);

    public List<SelectCombo> getUserComboList(HttpServletRequest request);
}
