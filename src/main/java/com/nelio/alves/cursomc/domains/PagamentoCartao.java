package com.nelio.alves.cursomc.domains;

import java.io.Serializable;

import javax.persistence.Entity;

import com.nelio.alves.cursomc.domains.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;

	public PagamentoCartao() {
		
	}

	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
