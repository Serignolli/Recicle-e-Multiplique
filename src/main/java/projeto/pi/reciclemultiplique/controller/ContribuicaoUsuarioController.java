package projeto.pi.reciclemultiplique.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import projeto.pi.reciclemultiplique.domain.Contribuicao;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.ContribuicaoRepository;

@Controller
@RequestMapping("/usuario")
public class ContribuicaoUsuarioController {

    private final ContribuicaoRepository contribuicaoRepository;

    public ContribuicaoUsuarioController(ContribuicaoRepository contribuicaoRepository){
        this.contribuicaoRepository = contribuicaoRepository;
    }
    
    @PostMapping("/criarContribuicao")
    public String criarContribuicao(
                                    @RequestParam String produto,
                                    @RequestParam Double peso,
                                    @RequestParam String descricao,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes
                                    ) {

        Contribuicao contribuicao = new Contribuicao();


        Usuario usuario = (Usuario) session.getAttribute("user");

        contribuicao.setProduto(produto);
        contribuicao.setPeso(peso);
        contribuicao.setDescricao(descricao);
        contribuicao.setUsuario(usuario);

        this.contribuicaoRepository.save(contribuicao);

        redirectAttributes.addFlashAttribute("mensagem", "Contribuição realizada com sucesso");

        return "/usuario/userPage";
    }

    @GetMapping("/mostrarContribuicaoUsuario")
    public String mostrarContribuicoes(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            List<Contribuicao> contribuicoes = contribuicaoRepository.findByUsuario(usuario);
        
            model.addAttribute("contribuicoes", contribuicoes);

            return "/usuario/userPage";
        } else {
            return "redirect:/auth/login";
        }
    }
}
