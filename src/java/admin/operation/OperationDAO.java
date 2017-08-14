/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.operation;

import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;

/**
 *
 * @author Vision
 */
public interface OperationDAO {

    void addOperation(OperationForm operationForm);

    public OperationForm editOperation(Integer id);

    void updateOperation(OperationForm operationForm);

    void deleteOperation(Integer id);

    TableForm getOperationList(TableForm tableFrom);

    public List<SelectCombo> getOperationComboList(HttpServletRequest request);
}
