package projeto.pi.reciclemultiplique.dao;

import java.util.List;

import projeto.pi.reciclemultiplique.entity.Usuario;

public class UsuarioDao {

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Long id);

    Usuario findById(Long id);

    List<Usuario> findAll();
}
