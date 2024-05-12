package projeto.pi.reciclemultiplique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.pi.reciclemultiplique.dao.UsuarioDao;
import projeto.pi.reciclemultiplique.domain.Usuario;

@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService {
	
	@Autowired
	private UsuarioDao dao;
	
	@Override
	public void salvar(Usuario usuario) {
		dao.save(usuario);
	}
	
	@Override
	public void editar(Usuario usuario) {
		dao.update(usuario);
	}
	
	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return dao.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return dao.findAll();
	}
}
