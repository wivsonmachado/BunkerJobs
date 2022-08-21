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
	
	@PostMapping("/add")
	public String novo(@Valid BunkerOperation operation, BindingResult result) {
		operationsService.operationAdd(operation, result);		
		return "redirect:/home";
	}

	@GetMapping("form/{id}")
	public String editForm(Model model, @PathVariable(value = "id") int id) {		
		BunkerOperation operation = operationsService.getOperationById(id);
		model.addAttribute("operation", operation);
		
		return "editForm";	
	}
	
	@PostMapping("update/{id}")
	public String update(@Valid BunkerOperation operation, BindingResult result, @PathVariable int id ) {		
		operationsService.operationAdd(operation, result);	
		return "redirect:/home";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable (value = "id") int id) {
		operationsService.delete(id);		
		return "redirect:/home";
	}
	
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model ) {
		int PageSize = 12;
		Page<BunkerOperation> page = operationsService.findPaginated(pageNo, PageSize);
		List<BunkerOperation> pageList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listOperations", pageList);
		
		return "home";
	}
	
	
}
