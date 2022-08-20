package wmcodes.bunker.operacoes.jobs.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;
import wmcodes.bunker.operacoes.jobs.repository.BunkerOperationRepository;
import wmcodes.bunker.operacoes.jobs.service.OperationsService;

@Controller
public class OperationController {

	@Autowired
	OperationsService operationsService;
	
	@GetMapping("/home")
	public String home(Model model) {
		return findPaginated(1, model);
	}
	
	
	@GetMapping("/form")
    public String operationForm(Model model, BunkerOperation operation) {
		model.addAttribute("operation", operation);
        return "addOperationForm";
    }
	
	
//	@PostMapping("/add")
//	public String novo(@Valid BunkerOperation operation, BindingResult result) {
//		if(result.hasFieldErrors()) {
//			formattInputDateTime(operation, result);
//		}
//		
//		bunkerOperationRepository.save(operation);
//		
//		return "redirect:/home";
//	}
//
//	@GetMapping("form/{id}")
//	public String editForm(Model model, @PathVariable(name = "id") int id) {
//		
//		BunkerOperation operation = bunkerOperationRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//		
//		model.addAttribute("operation", operation);
//		
//		return "editForm";
//		
//	
//	}
	
//	@PostMapping("update/{id}")
//	public String update(@Valid BunkerOperation operation, BindingResult result, @PathVariable int id ) {		
//		
//		if(result.hasErrors()) {
//			formattInputDateTime(operation, result);
//		}		
//		
//		bunkerOperationRepository.save(operation);		
//		return "redirect:/home";
//	}
//	
//	@GetMapping("delete/{id}")
//	public String delete(Model model, @PathVariable(name = "id") int id) {
//		
//		BunkerOperation operation = bunkerOperationRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//		
//		bunkerOperationRepository.delete(operation);
//		
//		return "redirect:/home";
//		
//	
//	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model ) {
		int PageSize = 15;
		Page<BunkerOperation> page = operationsService.findPaginated(pageNo, PageSize);
		List<BunkerOperation> pageList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listOperations", pageList);
		
		return "home";
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
