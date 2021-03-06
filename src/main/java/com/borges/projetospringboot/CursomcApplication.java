package com.borges.projetospringboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.borges.projetospringboot.domain.Categoria;
import com.borges.projetospringboot.domain.Cidade;
import com.borges.projetospringboot.domain.Cliente;
import com.borges.projetospringboot.domain.Endereco;
import com.borges.projetospringboot.domain.Estado;
import com.borges.projetospringboot.domain.Produto;
import com.borges.projetospringboot.domain.enums.TipoCliente;
import com.borges.projetospringboot.repositories.CategoriaRepository;
import com.borges.projetospringboot.repositories.CidadeRepository;
import com.borges.projetospringboot.repositories.ClienteRepository;
import com.borges.projetospringboot.repositories.EnderecoRepository;
import com.borges.projetospringboot.repositories.EstadoRepository;
import com.borges.projetospringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 2900.00);
		Produto p3 = new Produto(null, "Mouse", 700.00);
	
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 =  new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade (null, "Uberlândia", est1 );
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 =  new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria José", "ed_borg@hotmail.com", "04679629606", TipoCliente.PESSOAFISICA); 
		cli1.getTelefones().addAll(Arrays.asList("3591-8858", "98886-7399"));
		Endereco e1 =  new Endereco(null, "Rua Mensageiro", "26", "casas", "Teresópolis", "32681-408",cli1, c1 );
		Endereco e2 =  new Endereco(null, "Av Antônio Carlos", "601", "casa", "Teresópolis", "32681-415",cli1, c2 );
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

	



}



