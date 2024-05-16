package projeto.pi.reciclemultiplique.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import projeto.pi.reciclemultiplique.domain.Contribuicao;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.ContribuicaoRepository;

@RequestMapping("/usuario")
@RequiredArgsConstructor
public class ContribuicaoController {

    private final ContribuicaoRepository contribuicaoRepository;
    
    @PostMapping("/criarContribuicao")
    public String criarContribuicao(
                                    @RequestParam String produto,
                                    @RequestParam Double peso,
                                    @RequestParam String Descricao,
                                    @AuthenticationPrincipal Usuario usuario
                                    ) {

        Contribuicao contribuicao = new Contribuicao();

        contribuicao.setProduto(produto);
        contribuicao.setPeso(peso);
        contribuicao.setDescricao(Descricao);
        contribuicao.setUsuario(usuario);

        this.contribuicaoRepository.save(contribuicao);

        return "/usuario/userPage";
    }
}
