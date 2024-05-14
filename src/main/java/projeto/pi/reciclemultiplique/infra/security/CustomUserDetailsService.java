package projeto.pi.reciclemultiplique.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import projeto.pi.reciclemultiplique.domain.Usuario;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository
	
	@Override
	public UserDetails loadUderByUsername(String username) throws UsernameNotFoundException {
		Usuario user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
}
