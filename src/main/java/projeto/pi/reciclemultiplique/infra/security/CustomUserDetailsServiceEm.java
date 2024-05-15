package projeto.pi.reciclemultiplique.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import projeto.pi.reciclemultiplique.domain.Empresa;
import projeto.pi.reciclemultiplique.repositories.EmpresaRepository;

@Component
public class CustomUserDetailsServiceEm implements UserDetailsService {

    @Autowired
    private EmpresaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empresa empresa = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Empresa não encontrada"));
        return new org.springframework.security.core.userdetails.User(empresa.getEmail(), empresa.getSenha(), new ArrayList<>());
    }
}
