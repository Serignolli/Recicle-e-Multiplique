package projeto.pi.reciclemultiplique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projeto.pi.reciclemultiplique.entity.Endereco;
import projeto.pi.reciclemultiplique.entity.UF;
import projeto.pi.reciclemultiplique.entity.Usuario;
import projeto.pi.reciclemultiplique.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/cadastro")
    public String telaCadastro() {
    	return "/usuario/cadastro";
    }

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
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(UF.valueOf(uf));
        endereco.setCep(cep);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);

        usuario.setEndereco(endereco);

        usuarioService.salvar(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/usuario/cadastro";
    }


}
