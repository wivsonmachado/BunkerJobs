package wmcodes.bunker.operacoes.jobs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wmcodes.bunker.operacoes.jobs.model.BunkerOperation;
import wmcodes.bunker.operacoes.jobs.model.repository.BunkerOperationRepository;

@Controller
public class HomeController {

	@Autowired
	private BunkerOperationRepository bunkerOperationReposity;
	
	
	@GetMapping("/home")
	public String home(Model model) {
		List<BunkerOperation> bunkerOperations = bunkerOperationReposity.findAll(Sort.by(Sort.Direction.ASC, "dataOperacao"));
	
		model.addAttribute("bunkerOperations", bunkerOperations);
		return "home";
	}
}
