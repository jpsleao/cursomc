package com.nelio.alves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelio.alves.cursomc.dao.CategoriaDao;
import com.nelio.alves.cursomc.dao.CidadeDao;
import com.nelio.alves.cursomc.dao.ClienteDao;
import com.nelio.alves.cursomc.dao.EnderecoDao;
import com.nelio.alves.cursomc.dao.EstadoDao;
import com.nelio.alves.cursomc.dao.ProdutoDao;
import com.nelio.alves.cursomc.domains.Categoria;
import com.nelio.alves.cursomc.domains.Cidade;
import com.nelio.alves.cursomc.domains.Cliente;
import com.nelio.alves.cursomc.domains.Endereco;
import com.nelio.alves.cursomc.domains.Estado;
import com.nelio.alves.cursomc.domains.Produto;
import com.nelio.alves.cursomc.domains.enums.TipoCliente;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaDao categoriaDao;
	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private CidadeDao cidadeDao;
	@Autowired
    private EstadoDao estadoDao;
	@Autowired
	private EnderecoDao enderecoDao;
	@Autowired
	private ClienteDao clienteDao;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaDao.saveAll(Arrays.asList(cat1,cat2));
		produtoDao.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1); 
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoDao.saveAll(Arrays.asList(est1,est2));
		cidadeDao.saveAll(Arrays.asList(c1,c2,c3));	
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "11111111100", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("1111111","222222222"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		clienteDao.saveAll(Arrays.asList(cli1));
		enderecoDao.saveAll(Arrays.asList(e1,e2));
		
		
		
		
	}
}
