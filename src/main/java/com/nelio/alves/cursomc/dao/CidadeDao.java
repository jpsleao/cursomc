package com.nelio.alves.cursomc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelio.alves.cursomc.domains.Cidade;

@Repository
public interface CidadeDao extends JpaRepository<Cidade, Integer> {

}
