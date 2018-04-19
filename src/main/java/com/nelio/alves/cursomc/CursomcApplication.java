package com.nelio.alves.cursomc;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.nelio.alves.cursomc.dao.ItemPedidoDao;
import com.nelio.alves.cursomc.dao.PagamentoDao;
import com.nelio.alves.cursomc.dao.PedidoDao;
import com.nelio.alves.cursomc.dao.ProdutoDao;
import com.nelio.alves.cursomc.domains.Categoria;
import com.nelio.alves.cursomc.domains.Cidade;
import com.nelio.alves.cursomc.domains.Cliente;
import com.nelio.alves.cursomc.domains.Endereco;
import com.nelio.alves.cursomc.domains.Estado;
import com.nelio.alves.cursomc.domains.ItemPedido;
import com.nelio.alves.cursomc.domains.Pagamento;
import com.nelio.alves.cursomc.domains.PagamentoBoleto;
import com.nelio.alves.cursomc.domains.PagamentoCartao;
import com.nelio.alves.cursomc.domains.Pedido;
import com.nelio.alves.cursomc.domains.Produto;
import com.nelio.alves.cursomc.domains.enums.EstadoPagamento;
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
	@Autowired
	private PagamentoDao pagamentoDao;
	@Autowired
	private PedidoDao pedidoDao;
	@Autowired
	private ItemPedidoDao itemPedidoDao;
	
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
		
		DecimalFormat df = new DecimalFormat("000");
		
		for(Integer x=0; x<=100; x++) {
			Categoria cat = new Categoria(null, "Categoria " + df.format(x));
			categoriaDao.save(cat);			
		}
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoDao.saveAll(Arrays.asList(ped1,ped2));
		pagamentoDao.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoDao.saveAll(Arrays.asList(ip1,ip2,ip3));		
		
	}
}
