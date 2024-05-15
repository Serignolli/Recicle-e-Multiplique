package projeto.pi.reciclemultiplique.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.pi.reciclemultiplique.domain.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

    Optional<Empresa> findByEmail(String email);
}
