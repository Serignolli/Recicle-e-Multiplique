package projeto.pi.reciclemultiplique.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projeto.pi.reciclemultiplique.domain.Contribuicao;
import projeto.pi.reciclemultiplique.service.ContribuicaoService;

@Controller
@RequestMapping("/empresa")
public class ContribuicaoEmpresaController {

    private final ContribuicaoService contribuicaoService;

    public ContribuicaoEmpresaController(ContribuicaoService contribuicaoService){
        this.contribuicaoService = contribuicaoService;
    }

    @GetMapping("/mostrarContribuicao")
    public String mostarContribuicao(Model model){

        List<Contribuicao> contribuicoes = contribuicaoService.getAllContribuicoes();
    
        model.addAttribute("contribuicoes", contribuicoes);

        return "/empresa/companyPage";
    }
}
