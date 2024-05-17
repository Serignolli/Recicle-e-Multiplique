package projeto.pi.reciclemultiplique.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import projeto.pi.reciclemultiplique.domain.Contribuicao;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.ContribuicaoRepository;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class ContribuicaoUsuarioController {

    private final ContribuicaoRepository contribuicaoRepository;

    public ContribuicaoUsuarioController(ContribuicaoRepository contribuicaoRepository){
        this.contribuicaoRepository = contribuicaoRepository;
    }
    
    @PostMapping("/criarContribuicao")
    public String criarContribuicao(
                                    @RequestParam String produto,
                                    @RequestParam Double peso,
                                    @RequestParam String Descricao,
                                    @AuthenticationPrincipal Usuario usuario,
                                    RedirectAttributes redirectAttributes
                                    ) {

        Contribuicao contribuicao = new Contribuicao();

        contribuicao.setProduto(produto);
        contribuicao.setPeso(peso);
        contribuicao.setDescricao(Descricao);
        contribuicao.setUsuario(usuario);

        this.contribuicaoRepository.save(contribuicao);

        redirectAttributes.addFlashAttribute("mensagem", "Contribuição realizada com sucesso");

        return "/usuario/userPage";
    }

    @GetMapping("/mostrarContribuicaoUsuario")
    public String mostrarContribuicoes(@AuthenticationPrincipal Usuario usuario, Model model) {

        List<Contribuicao> contribuicoes = contribuicaoRepository.findByUsuario(usuario);

        List<String> nomesUsuarios = new ArrayList<>();
        List<String> sobrenomesUsuarios = new ArrayList<>();
    
        for (Contribuicao contribuicao : contribuicoes) {
            Usuario usuarioContribuicao = contribuicao.getUsuario();
            nomesUsuarios.add(usuarioContribuicao.getNome());
            sobrenomesUsuarios.add(usuarioContribuicao.getSobrenome());
        }
    
        model.addAttribute("contribuicoes", contribuicoes);
        model.addAttribute("nomesUsuarios", nomesUsuarios);
        model.addAttribute("sobrenomesUsuarios", sobrenomesUsuarios);

        return "/usuario/userPage";
    }
}
