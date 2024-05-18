package projeto.pi.reciclemultiplique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@GetMapping("/userPage")
	public String userPage() {
		return "/usuario/userPage";
	}
}
