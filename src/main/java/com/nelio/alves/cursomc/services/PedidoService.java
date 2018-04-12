package com.nelio.alves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelio.alves.cursomc.dao.PedidoDao;
import com.nelio.alves.cursomc.domains.Pedido;
import com.nelio.alves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoDao dao;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));	   	
	}

}
