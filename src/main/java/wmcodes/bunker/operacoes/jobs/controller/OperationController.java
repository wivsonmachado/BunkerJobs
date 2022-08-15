package wmcodes.bunker.operacoes.jobs.controller;

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
	
	
	@PostMapping("/add")
	public String novo(@Valid BunkerOperation operation, BindingResult result) {
		if(result.hasFieldErrors()) {
			return "redirect:/form";
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
			return "redirect:/form";
		}
		
		bunkerOperationRepository.save(operation);		
		return "redirect:/home";
	}
	
}
