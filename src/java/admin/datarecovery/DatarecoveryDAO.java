/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.datarecovery;

import javax.servlet.http.HttpServletRequest;
import admin.filter.SelectCombo;
import java.util.List;
import admin.filter.TableForm;

/**
 *
 * @author Vision
 */
public interface DatarecoveryDAO {

    void addDatarecovery(DatarecoveryForm datarecoveryForm);

    public DatarecoveryForm editDatarecovery(Integer id);

    void updateDatarecovery(DatarecoveryForm datarecoveryForm);

    void deleteDatarecovery(Integer id);

    TableForm getDatarecoveryList(TableForm tableFrom);

    public List<SelectCombo> getDatarecoveryComboList(HttpServletRequest request);
}
