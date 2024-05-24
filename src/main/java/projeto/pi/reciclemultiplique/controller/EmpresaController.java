package projeto.pi.reciclemultiplique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	@GetMapping("/companyPage")
	public String companyPage(HttpSession session) {

		if (session.getAttribute("company") != null) {
            return "/empresa/companyPage";
        } else {
            return "redirect:/auth/loginPage";
        }
	}

    @GetMapping("/alter/alterPage")
    public String alterPage(HttpSession session) {

        if (session.getAttribute("company") != null) {
            return "/empresa/alter/alterPage";
        } else {
            return "redirect:/auth/loginPage";
        }
    }
}
