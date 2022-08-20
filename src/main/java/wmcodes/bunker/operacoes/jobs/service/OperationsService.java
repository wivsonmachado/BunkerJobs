package wmcodes.bunker.operacoes.jobs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;
import wmcodes.bunker.operacoes.jobs.repository.BunkerOperationRepository;

@Service
public class OperationsService implements IOperationsService{

	@Autowired
	BunkerOperationRepository bunkerOperationRepository;
	
	
	@Override
	public Page<BunkerOperation> findPaginated(int pageNo, int pageSize) {
		
		Pageable paging = PageRequest.of(pageNo - 1, pageSize);		
		return this.bunkerOperationRepository.findAll(paging);
	}

}
