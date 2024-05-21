package projeto.pi.reciclemultiplique.controller;

import java.util.Optional;

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

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private final UsuarioRepository usuarioRepository;
	private final EmpresaRepository empresaRepository;
	
    public AuthController(UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository) {
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
	    
	    Optional<Usuario> existingUser = this.usuarioRepository.findByEmail(email);

	    if(existingUser.isEmpty()) {
	        Endereco endereco = new Endereco();
	        endereco.setLogradouro(logradouro);
	        endereco.setBairro(bairro);
	        endereco.setCidade(cidade);
	        endereco.setUf(UF.valueOf(uf));
	        endereco.setCep(cep);
	        endereco.setNumero(numero);
	        endereco.setComplemento(complemento);
	        
	        Usuario newUser = new Usuario();
	        newUser.setEmail(email);
	        newUser.setSenha(senha);
	        newUser.setNome(nome);
	        newUser.setSobrenome(sobrenome);
	        newUser.setCpf(cpf);
	        newUser.setEndereco(endereco);
	        
	        this.usuarioRepository.save(newUser);

	        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
	        return "redirect:/auth/loginPage";
	    } else {
	        model.addAttribute("erro", "Usuário já cadastrado");
	        return "/auth/registration";
	    }
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
	    
	    Optional<Empresa> existingCompany = this.empresaRepository.findByEmail(email);

	    if(existingCompany.isEmpty()) {
	        Endereco endereco = new Endereco();
	        endereco.setLogradouro(logradouro);
	        endereco.setBairro(bairro);
	        endereco.setCidade(cidade);
	        endereco.setUf(UF.valueOf(uf));
	        endereco.setCep(cep);
	        endereco.setNumero(numero);
	        endereco.setComplemento(complemento);
	        
	        Empresa newCompany = new Empresa();
	        newCompany.setEmail(email);
	        newCompany.setSenha(senha);
	        newCompany.setNomeEmpresa(nomeEmpresa);
	        newCompany.setCnpj(cnpj);
	        newCompany.setEndereco(endereco);
	        
	        this.empresaRepository.save(newCompany);

	        redirectAttributes.addFlashAttribute("mensagem", "Empresaa cadastrada com sucesso!");
	        return "redirect:/auth/loginPage";
	    } else {
	        model.addAttribute("erro", "Empresa já cadastrada");
	        return "/auth/registration";
	    }
	}

	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/loginPage";
    }

}
