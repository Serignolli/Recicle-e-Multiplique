package projeto.pi.reciclemultiplique.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.pi.reciclemultiplique.dao.EmpresaDao;
import projeto.pi.reciclemultiplique.entity.Empresa;

@Service
@Transactional
public class EmpresaServiceImp implements EmpresaService {
	
	@Autowired
	private EmpresaDao dao;
	
	@Override
	public void salvar(Empresa empresa) {
		dao.save(empresa);
	}
	
	@Override
	public void editar(Empresa empresa) {
		dao.update(empresa);
	}
	
	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		return dao.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
		return dao.findAll();
	}
}
