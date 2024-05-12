package projeto.pi.reciclemultiplique.service;

import java.util.List;

import projeto.pi.reciclemultiplique.domain.Usuario;

public interface UsuarioService {
	
	void salvar(Usuario usuario);
	
	void editar(Usuario usuario);
	
	void excluir(Long id);
	
	Usuario buscarPorId(Long id);
	
	List<Usuario> buscarTodos();
}
