package wmcodes.bunker.operacoes.jobs.controller;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;
import wmcodes.bunker.operacoes.jobs.model.repository.BunkerOperationRepository;

@Controller
public class OperationController {

	@Autowired
	BunkerOperationRepository bunkerOperationRepository;
	
	@GetMapping("/form")
    public String operationForm(Model model, BunkerOperation operation) {
		model.addAttribute("operation", operation);
        return "addOperationForm";
    }
	
	
	@PostMapping("/add")
	public String novo(@Valid BunkerOperation operation, BindingResult result) {
		if(result.hasFieldErrors()) {
			formattInputDateTime(operation, result);
		}
		
		bunkerOperationRepository.save(operation);
		
		return "redirect:/home";
	}

	@GetMapping("form/{id}")
	public String editForm(Model model, @PathVariable(name = "id") int id) {
		
		BunkerOperation operation = bunkerOperationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		model.addAttribute("operation", operation);
		
		return "editForm";
		
	
	}
	
	@PostMapping("update/{id}")
	public String update(@Valid BunkerOperation operation, BindingResult result, @PathVariable int id ) {		
		
		if(result.hasErrors()) {
			formattInputDateTime(operation, result);
		}		
		
		bunkerOperationRepository.save(operation);		
		return "redirect:/home";
	}
	
	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable(name = "id") int id) {
		
		BunkerOperation operation = bunkerOperationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		bunkerOperationRepository.delete(operation);
		
		return "redirect:/home";
		
	
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
