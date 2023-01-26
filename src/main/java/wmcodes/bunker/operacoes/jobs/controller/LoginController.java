package wmcodes.bunker.operacoes.jobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;

@Controller
public class LoginController {

	@GetMapping("/")
	public String operationForm(Model model, BunkerOperation operation) {
			model.addAttribute("operation", operation);
	        return "addOperationForm";
	}
	
	@GetMapping("/login")
	public String login() {
	        return "login";
	}
	
}
