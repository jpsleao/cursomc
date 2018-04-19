package com.nelio.alves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelio.alves.cursomc.dao.CategoriaDao;
import com.nelio.alves.cursomc.domains.Categoria;
import com.nelio.alves.cursomc.services.exceptions.DataIntegrityException;
import com.nelio.alves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDao dao;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));	   	
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return dao.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return dao.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			dao.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return dao.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return dao.findAll(pageRequest);
	}
}
