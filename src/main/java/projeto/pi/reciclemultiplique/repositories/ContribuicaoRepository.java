package projeto.pi.reciclemultiplique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.pi.reciclemultiplique.domain.Contribuicao;

public interface ContribuicaoRepository extends JpaRepository<Contribuicao, Long>{

}
