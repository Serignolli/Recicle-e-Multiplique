package projeto.pi.reciclemultiplique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	@GetMapping("/companyPage")
	public String companyPage() {
		return "/empresa/companyPage";
	}
}
