package projeto.pi.reciclemultiplique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.pi.reciclemultiplique.domain.Contribuicao;
import projeto.pi.reciclemultiplique.domain.Usuario;

public interface ContribuicaoRepository extends JpaRepository<Contribuicao, Long>{

    List<Contribuicao> findByUsuario(Usuario usuario);

}
