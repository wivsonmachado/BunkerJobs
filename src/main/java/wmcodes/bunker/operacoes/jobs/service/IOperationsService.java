package wmcodes.bunker.operacoes.jobs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;

public interface IOperationsService {
	
	void operationAdd(BunkerOperation operation, BindingResult result);
	BunkerOperation getOperationById(int id);
	void delete(int id);
	Page<BunkerOperation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
