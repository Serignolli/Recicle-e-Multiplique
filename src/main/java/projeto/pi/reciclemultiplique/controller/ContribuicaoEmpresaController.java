package projeto.pi.reciclemultiplique.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projeto.pi.reciclemultiplique.domain.Contribuicao;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.ContribuicaoRepository;

@Controller
@RequestMapping("/empresa")
public class ContribuicaoEmpresaController {

    private final ContribuicaoRepository contribuicaoRepository;

    public ContribuicaoEmpresaController(ContribuicaoRepository contribuicaoRepository){
        this.contribuicaoRepository = contribuicaoRepository;
    }

    @GetMapping("/mostrarContribuicao")
    public String mostarContribuicao(Model model){

        List<Contribuicao> contribuicoes = contribuicaoRepository.findAll();

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

        return "/empresa/companyPage";
    }
}
