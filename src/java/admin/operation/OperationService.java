/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.operation;

import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Operation Vision
 */
public interface OperationService {

    void addOperation(OperationForm operationForm);

    void updateOperation(OperationForm operationForm);

    void deleteOperation(Integer id);

    TableForm getOperationList(TableForm taleform);

    public OperationForm editOperation(Integer id);

    public List<SelectCombo> getOperationComboList(HttpServletRequest request);
}
