package projeto.pi.reciclemultiplique.dao;

import java.util.List;

import projeto.pi.reciclemultiplique.entity.Empresa;

public class EmpresaDao {

    void save(Empresa empresa);

    void update(Empresa empresa);

    void delete(Long id);

    Empresa findById(Long id);

    List<Empresa> findAll();
}
