package projeto.pi.reciclemultiplique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import projeto.pi.reciclemultiplique.entity.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping("/cadastrar")
    @ResponseBody
    public String cadastrar(@RequestBody Usuario usuario) {
        return "Usuário cadastrado com sucesso!";
    }
}
