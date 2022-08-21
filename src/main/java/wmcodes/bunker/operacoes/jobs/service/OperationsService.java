package wmcodes.bunker.operacoes.jobs.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;
import wmcodes.bunker.operacoes.jobs.repository.BunkerOperationRepository;

@Service
public class OperationsService implements IOperationsService{

	@Autowired
	BunkerOperationRepository bunkerOperationRepository;
	
	@Override
	public void operationAdd(BunkerOperation operation, BindingResult result) {		
		if(result.hasFieldErrors()) {
			formattInputDateTime(operation, result);
		}
		
		this.bunkerOperationRepository.save(operation);
	}

	@Override
	public BunkerOperation getOperationById(int id) {
		Optional<BunkerOperation> optional = bunkerOperationRepository.findById(id);
		BunkerOperation operation = null;
		
		if(optional.isPresent()) {
			operation = optional.get();
		} else {
			throw new RuntimeCryptoException("Operação não encontrada pelo id: " + id);
		}
		
		return operation;
	}

	@Override
	public void delete(int id) {	
		this.bunkerOperationRepository.deleteById(id);
	}
	
	@Override
	public Page<BunkerOperation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();		
		
		Pageable paging = PageRequest.of(pageNo - 1, pageSize, sort);		
		return this.bunkerOperationRepository.findAll(paging);
	}

	private void formattInputDateTime(BunkerOperation operation, BindingResult result) {
		String dateTimeArray[] = new String[2];		
		String dateTimeRaw = result.getFieldValue("inicio").toString();
		dateTimeArray = dateTimeRaw.split("T");
		Timestamp dateTimeFormatted = Timestamp.valueOf(dateTimeArray[0] + " " + dateTimeArray[1] + ":00");
		operation.setInicio(dateTimeFormatted);
		
		dateTimeRaw = result.getFieldValue("fim").toString();
		dateTimeArray = dateTimeRaw.split("T");
		dateTimeFormatted = Timestamp.valueOf(dateTimeArray[0] + " " + dateTimeArray[1] + ":00");
		operation.setFim(dateTimeFormatted);
	}	
	

}
