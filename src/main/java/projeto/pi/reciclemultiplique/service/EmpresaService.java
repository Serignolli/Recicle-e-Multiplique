package projeto.pi.reciclemultiplique.service;

import java.util.List;

import projeto.pi.reciclemultiplique.entity.Empresa;

public interface EmpresaService {
	
	void salvar(Empresa empresa);
	
	void editar(Empresa empresa);
	
	void excluir(Long id);
	
	Empresa buscarPorId(Long id);
	
	List<Empresa> buscarTodos();
}
