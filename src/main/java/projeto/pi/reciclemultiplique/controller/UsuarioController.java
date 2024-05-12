package projeto.pi.reciclemultiplique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projeto.pi.reciclemultiplique.domain.Endereco;
import projeto.pi.reciclemultiplique.domain.UF;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.service.UsuarioService;

//Controller das funções do usuário
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    //Instância da service do usuário
    @Autowired
    private UsuarioService usuarioService;
    
    //Request da página de cadastro do usuário
    @GetMapping("/cadastro")
    public String telaCadastro() {
    	return "/usuario/cadastro";
    }

    //Inserção de cadastro do usuário
    @PostMapping("/cadastrar")
    public String cadastrar(@Validated Usuario usuario,
                            @RequestParam String logradouro,
                            @RequestParam String bairro,
                            @RequestParam String cidade,
                            @RequestParam String uf,
                            @RequestParam String cep,
                            @RequestParam Integer numero,
                            @RequestParam(required = false) String complemento,
                            RedirectAttributes redirectAttributes) {

        //Inserção dos dados de endereço
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(UF.valueOf(uf));
        endereco.setCep(cep);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);

        //Inserção do endereço no usuário
        usuario.setEndereco(endereco);

        //Inserção do usuário
        usuarioService.salvar(usuario);

        //Mensagem e sucesso com redirecionamento para a página de login
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/usuario/login";
    }

    //Request da página de login do usuário
    @GetMapping("/login")
    public String telaLogin() {
    	return "/usuario/login";
    }
}
