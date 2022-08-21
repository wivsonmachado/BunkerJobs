package wmcodes.bunker.operacoes.jobs.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;

public interface IOperationsService {
	
	List<BunkerOperation> findAllOperation();
	void operationAdd(BunkerOperation operation, BindingResult result);
	BunkerOperation getOperationById(int id);
	void delete(int id);

}
