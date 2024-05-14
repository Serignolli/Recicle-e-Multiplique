package projeto.pi.reciclemultiplique.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.pi.reciclemultiplique.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
}