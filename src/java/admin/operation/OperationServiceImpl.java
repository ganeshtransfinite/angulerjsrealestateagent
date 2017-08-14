package admin.operation;

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
public class OperationServiceImpl implements OperationService {

    @Autowired(required = true)
    OperationDAO operationDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOperation(OperationForm operationForm) {
        operationDAO.addOperation(operationForm);
    }

    @Override
    @Transactional
    public TableForm getOperationList(TableForm tableform) {
        return operationDAO.getOperationList(tableform);
    }

    @Override
    @Transactional
    public OperationForm editOperation(Integer id) {
        return operationDAO.editOperation(id);
    }

    @Override
    @Transactional
    public void updateOperation(OperationForm operationForm) {
        operationDAO.updateOperation(operationForm);
    }

    @Override
    @Transactional
    public void deleteOperation(Integer id) {
        operationDAO.deleteOperation(id);
    }

    @Override
    @Transactional
    public List<SelectCombo> getOperationComboList(HttpServletRequest request) {
        return operationDAO.getOperationComboList(request);
    }
}
