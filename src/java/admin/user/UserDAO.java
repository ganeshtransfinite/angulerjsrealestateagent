/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.user;

import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;

/**
 *
 * @author Vision
 */
public interface UserDAO {

    void addUser(UserForm userForm);

    public UserForm editUser(Integer id);

    String updateUser(UserForm userForm);

    void deleteUser(Integer id);

    TableForm getUserList(TableForm tableFrom);
public boolean checkExist(String col, String val, int id);
    public List<SelectCombo> getUserComboList(HttpServletRequest request);
}
