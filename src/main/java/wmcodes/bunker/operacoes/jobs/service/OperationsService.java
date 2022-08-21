package wmcodes.bunker.operacoes.jobs.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;
import wmcodes.bunker.operacoes.jobs.repository.BunkerOperationRepository;

@Service
public class OperationsService implements IOperationsService{

	@Autowired
	BunkerOperationRepository bunkerOperationRepository;

	@Override
	public List<BunkerOperation> findAllOperation() {
		return bunkerOperationRepository.findAll();
	}	
	
	@Override
	public void operationAdd(BunkerOperation operation, BindingResult result) {		
		if(result.hasFieldErrors()) {
			formattInputDateTime(operation, result);
		}
		
		BigDecimal recebido = new BigDecimal(result.getFieldValue("recebido").toString());
		BigDecimal fornecido = new BigDecimal(result.getFieldValue("fornecido").toString());
		BigDecimal diferenca = recebido.subtract(fornecido);
		
		Double porcentagem = diferenca.divide(recebido, 2, RoundingMode.HALF_EVEN).doubleValue() * 100;
		
		operation.setDiferenca(diferenca);
		operation.setPorcentagem(porcentagem);
		
		
		
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
