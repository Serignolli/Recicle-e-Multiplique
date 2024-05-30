package projeto.pi.reciclemultiplique.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projeto.pi.reciclemultiplique.domain.Contribuicao;
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
    
        model.addAttribute("contribuicoes", contribuicoes);

        return "/empresa/companyPage";
    }
}
