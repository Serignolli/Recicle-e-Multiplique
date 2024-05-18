package projeto.pi.reciclemultiplique.infra.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.UsuarioRepository;

@Component
public class CustomUserDetailsServiceUs implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), authorities);
    }
}
