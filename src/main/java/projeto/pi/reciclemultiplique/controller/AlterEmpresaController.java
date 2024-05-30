package projeto.pi.reciclemultiplique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import projeto.pi.reciclemultiplique.domain.Empresa;
import projeto.pi.reciclemultiplique.service.EmpresaService;

@Controller
@RequestMapping("/empresa/alter")
public class AlterEmpresaController {

    @Autowired
    private EmpresaService empresaService;
    
    @PostMapping("/alterar")
    public String alterar(@RequestParam String email, @RequestParam String senha, RedirectAttributes redirectAttributes, HttpSession session) {

        Empresa empresa = (Empresa) session.getAttribute("empresa");
        if (empresa == null) {
            redirectAttributes.addFlashAttribute("erro", "Usuário não está autenticado");
        }
        
        empresaService.alterarEmpresa(empresa.getId(), email, senha);
        redirectAttributes.addFlashAttribute("mensagem", "Perfil atualizado com sucesso!");

        return "redirect:/auth/loginPage";
    }
    
    @PostMapping("/deletar")
    public String deletar(RedirectAttributes redirectAttributes, HttpSession session) {

        Empresa empresa = (Empresa) session.getAttribute("empresa");
        if (empresa == null) {
            redirectAttributes.addFlashAttribute("erro", "Usuário não está autenticado");
        }
        
        empresaService.deletarEmpresa(empresa.getId());
        redirectAttributes.addFlashAttribute("mensagem", "Perfil atualizado com sucesso!");
        return "redirect:/auth/loginPage";
    }
}
