package projeto.pi.reciclemultiplique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.pi.reciclemultiplique.domain.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{

}
