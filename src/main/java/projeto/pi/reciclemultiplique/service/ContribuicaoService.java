package projeto.pi.reciclemultiplique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.pi.reciclemultiplique.domain.Contribuicao;
import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.ContribuicaoRepository;

@Service
public class ContribuicaoService {

    @Autowired
    private ContribuicaoRepository contribuicaoRepository;

    @Transactional(readOnly = true)
    public List<Contribuicao> getContribuicoesByUsuario(Usuario usuario) {
        return contribuicaoRepository.findByUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public List<Contribuicao> getAllContribuicoes() {
        return contribuicaoRepository.findAll();
    }
    
    @Transactional
    public Contribuicao salvarContribuicao(Contribuicao contribuicao) {
        return contribuicaoRepository.save(contribuicao);
    }
}
