package com.nelio.alves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelio.alves.cursomc.dao.CategoriaDao;
import com.nelio.alves.cursomc.domains.Categoria;
import com.nelio.alves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDao dao;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));	   	
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return dao.save(obj);
	}

}
