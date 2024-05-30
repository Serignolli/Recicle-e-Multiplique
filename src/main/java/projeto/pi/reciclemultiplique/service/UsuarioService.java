package projeto.pi.reciclemultiplique.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.pi.reciclemultiplique.domain.Usuario;
import projeto.pi.reciclemultiplique.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public String inserirUsuario(Usuario usuario) {

        Optional<Usuario> existingUser = getUsuarioByEmail(usuario.getEmail());

        if (existingUser.isPresent()) {
            return "Email já registrado";
        }

        this.usuarioRepository.save(usuario);

        return "Usuário registrado com sucesso";
    }

    @Transactional
    public void alterarUsuario(Long Id, String email, String senha) {
        Usuario usuario = usuarioRepository.findById(Id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletarUsuario(Long Id) {
        usuarioRepository.deleteById(Id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
