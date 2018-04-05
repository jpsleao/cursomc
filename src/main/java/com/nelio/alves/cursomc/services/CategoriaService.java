package com.nelio.alves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelio.alves.cursomc.dao.CategoriaDao;
import com.nelio.alves.cursomc.domains.Categoria;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDao dao;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElse(null);	   	
	}

}
