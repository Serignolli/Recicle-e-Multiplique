package projeto.pi.reciclemultiplique.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.pi.reciclemultiplique.domain.Empresa;
import projeto.pi.reciclemultiplique.repositories.EmpresaRepository;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public String inserirEmpresa(Empresa empresa) {

        Optional<Empresa> existingCompany = getEmpresaByEmail(empresa.getEmail());

        if (existingCompany.isPresent()) {
            return "Email já registrado";
        }

        this.empresaRepository.save(empresa);

        return "Empresa registrada com sucesso";
    }

    @Transactional
    public void alterarEmpresa(Long id, String email, String senha) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        empresa.setEmail(email);
        empresa.setSenha(senha);
        empresaRepository.save(empresa);
    }

    @Transactional
    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Empresa> getEmpresaByEmail(String email) {
        return empresaRepository.findByEmail(email);
    }
}
