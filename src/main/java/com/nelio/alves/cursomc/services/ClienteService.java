package com.nelio.alves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelio.alves.cursomc.dao.ClienteDao;
import com.nelio.alves.cursomc.domains.Cliente;
import com.nelio.alves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDao dao;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));	   	
	}

}
