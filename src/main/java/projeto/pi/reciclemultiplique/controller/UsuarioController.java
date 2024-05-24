package projeto.pi.reciclemultiplique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@GetMapping("/userPage")
    public String userPage(HttpSession session) {

        if (session.getAttribute("user") != null) {
            return "/usuario/userPage";
        } else {
            return "redirect:/auth/loginPage";
        }
    }

    @GetMapping("/alter/alterPage")
    public String alterPage(HttpSession session) {

        if (session.getAttribute("user") != null) {
            return "/usuario/alter/alterPage";
        } else {
            return "redirect:/auth/loginPage";
        }
    }
}
