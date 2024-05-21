package projeto.pi.reciclemultiplique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import projeto.pi.reciclemultiplique.domain.Usuario;


@Controller
@RequestMapping("/usuario/alter")
public class AlterUsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/alterar")
    public String alterar(@RequestParam String email, @RequestParam String senha, RedirectAttributes redirectAttributes, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("erro", "Usuário não está autenticado");
        }
        
        usuarioService.updateUser(usuario.getId(), email, senha);
        redirectAttributes.addFlashAttribute("mensagem", "Perfil atualizado com sucesso!");

        return "redirect:/auth/loginPage";
    }
    
    @PostMapping("/deletar")
    public String deletar(RedirectAttributes redirectAttributes, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("erro", "Usuário não está autenticado");
        }
        
        usuarioService.deletarUsuario(usuario.getId());
        redirectAttributes.addFlashAttribute("mensagem", "Perfil atualizado com sucesso!");
        return "redirect:/auth/loginPage";
    }
}
