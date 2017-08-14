/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.datarecovery;

import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Datarecovery Vision
 */
public interface DatarecoveryService {

    void addDatarecovery(DatarecoveryForm datarecoveryForm);

    void updateDatarecovery(DatarecoveryForm datarecoveryForm);

    void deleteDatarecovery(Integer id);

    TableForm getDatarecoveryList(TableForm taleform);

    public DatarecoveryForm editDatarecovery(Integer id);

    public List<SelectCombo> getDatarecoveryComboList(HttpServletRequest request);
}
