package wmcodes.bunker.operacoes.jobs.service;

import org.springframework.data.domain.Page;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;

public interface IOperationsService {
	
	Page<BunkerOperation> findPaginated(int pageNo, int pageSize);

}
