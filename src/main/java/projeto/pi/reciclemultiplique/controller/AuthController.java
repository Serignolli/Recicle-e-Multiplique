package projeto.pi.reciclemultiplique.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import projeto.pi.reciclemultiplique.domain.Endereco;
import projeto.pi.reciclemultiplique.domain.UF;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.dto.LoginRequestDTO;
import projeto.pi.reciclemultiplique.dto.ResponseDTO;
import projeto.pi.reciclemultiplique.infra.security.TokenService;
import projeto.pi.reciclemultiplique.repositories.UsuarioRepository;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
    //Request da página de cadastro do usuário
    @GetMapping("/registration")
    public String RegistrationScreen() {
    	return "/auth/registration";
    }
    
    //Request da página de login do usuário
    @GetMapping("/login")
    public String LoginScreen() {
    	return "/auth/login";
    }
    
	private final UsuarioRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO body) {
		Usuario user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/register")
	public String register(@RequestParam String email,
	                                @RequestParam String senha,
	                                @RequestParam String nome,
	                                @RequestParam String logradouro,
	                                @RequestParam String bairro,
	                                @RequestParam String cidade,
	                                @RequestParam String uf,
	                                @RequestParam String cep,
	                                @RequestParam Integer numero,
	                                @RequestParam(required = false) String complemento,
	                                RedirectAttributes redirectAttributes,
	                                Model model) {
	    
	    Optional<Usuario> existingUser = this.repository.findByEmail(email);

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
	        newUser.setSenha(passwordEncoder.encode(senha));
	        newUser.setNome(nome);
	        newUser.setEndereco(endereco);
	        
	        this.repository.save(newUser);

	        String token = this.tokenService.generateToken(newUser);

	        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
	        return "redirect:/auth/login";
	    } else {
	        model.addAttribute("erro", "Usuário já cadastrado");
	        return "redirect:/auth/registration";
	    }
	}
}
