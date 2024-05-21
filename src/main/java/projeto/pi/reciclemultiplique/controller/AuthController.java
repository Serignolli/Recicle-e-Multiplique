package projeto.pi.reciclemultiplique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import projeto.pi.reciclemultiplique.domain.Empresa;
import projeto.pi.reciclemultiplique.domain.Endereco;
import projeto.pi.reciclemultiplique.domain.UF;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.EmpresaRepository;
import projeto.pi.reciclemultiplique.repositories.UsuarioRepository;
import projeto.pi.reciclemultiplique.service.EmpresaService;
import projeto.pi.reciclemultiplique.service.UsuarioService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private final UsuarioService usuarioService;
	private final EmpresaService empresaService;
	private final UsuarioRepository usuarioRepository;
	private final EmpresaRepository empresaRepository;
	
    public AuthController(UsuarioService usuarioService, EmpresaService empresaService,
                          UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository) {
		this.usuarioService = usuarioService;
		this.empresaService = empresaService;
		this.usuarioRepository = usuarioRepository;
		this.empresaRepository = empresaRepository;
	}
			
    @GetMapping("/registrationPage")
    public String registrationPage() {
    	return "/auth/registration";
    }
    
    @GetMapping("/loginPage")
    public String loginPage() {
    	return "/auth/login";
    }
	
	@PostMapping("/loginUs")
	public String loginUs(@RequestParam String email,
						   @RequestParam String senha,
						   HttpSession session,
						   RedirectAttributes redirectAttributes,
						   Model model) {
	
		Usuario usuario = this.usuarioRepository.findByEmail(email).orElse(null);
	
		if (usuario != null && senha.matches(usuario.getSenha())){

			session.setAttribute("user", usuario);
			return "redirect:/usuario/userPage";
		} else {
			model.addAttribute("erro", "Credenciais incorretas");
			return "/auth/login";
		}
	}

	
	@PostMapping("/registerUs")
	public String registerUs(@RequestParam String email,
	                                @RequestParam String senha,
	                                @RequestParam String nome,
	                                @RequestParam String sobrenome,
	                                @RequestParam String cpf,
	                                @RequestParam String logradouro,
	                                @RequestParam String bairro,
	                                @RequestParam String cidade,
	                                @RequestParam String uf,
	                                @RequestParam String cep,
	                                @RequestParam Integer numero,
	                                @RequestParam(required = false) String complemento,
	                                RedirectAttributes redirectAttributes,
	                                Model model) {
	    
	    Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(UF.valueOf(uf));
        endereco.setCep(cep);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setCpf(cpf);
        usuario.setEndereco(endereco);

        String result = usuarioService.inserirUsuario(usuario);

        redirectAttributes.addFlashAttribute("mensagem", result);
        return "redirect:/auth/loginPage";
	}

	@PostMapping("/loginEm")
	public String loginEm(@RequestParam String email,
						   @RequestParam String senha,
						   HttpSession session,
						   RedirectAttributes redirectAttributes,
						   Model model) {
	
		Empresa empresa = this.empresaRepository.findByEmail(email).orElse(null);
	
		if (empresa != null && senha.matches(empresa.getSenha())) {

			session.setAttribute("company", empresa);
			return "redirect:/empresa/companyPage";
		} else {
			model.addAttribute("erro", "Credenciais incorretas");
			return "/auth/login";
		}
	}

	@PostMapping("/registerEm")
	public String registerEm(@RequestParam String email,
	                                @RequestParam String senha,
	                                @RequestParam String nomeEmpresa,
	                                @RequestParam String cnpj,
	                                @RequestParam String logradouro,
	                                @RequestParam String bairro,
	                                @RequestParam String cidade,
	                                @RequestParam String uf,
	                                @RequestParam String cep,
	                                @RequestParam Integer numero,
	                                @RequestParam(required = false) String complemento,
	                                RedirectAttributes redirectAttributes,
	                                Model model) {
	    
	    Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(UF.valueOf(uf));
        endereco.setCep(cep);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);

        Empresa empresa = new Empresa();
        empresa.setEmail(email);
        empresa.setSenha(senha);
        empresa.setNomeEmpresa(nomeEmpresa);
        empresa.setCnpj(cnpj);
        empresa.setEndereco(endereco);

        String result = empresaService.inserirEmpresa(empresa);

        redirectAttributes.addFlashAttribute("mensagem", result);
        return "redirect:/auth/loginPage";
	}

	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/loginPage";
    }

}
