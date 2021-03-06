package com.nelio.alves.cursomc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelio.alves.cursomc.domains.Pagamento;

@Repository
public interface PagamentoDao extends JpaRepository<Pagamento, Integer> {

}
