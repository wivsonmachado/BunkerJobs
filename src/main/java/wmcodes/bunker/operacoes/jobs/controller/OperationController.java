package wmcodes.bunker.operacoes.jobs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;
import wmcodes.bunker.operacoes.jobs.service.OperationsService;

@Controller
public class OperationController {

	@Autowired
	OperationsService operationsService;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<BunkerOperation> operations = operationsService.findAllOperation();
		model.addAttribute("listOperations", operations);
		
		return "home";
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
	
	
}
