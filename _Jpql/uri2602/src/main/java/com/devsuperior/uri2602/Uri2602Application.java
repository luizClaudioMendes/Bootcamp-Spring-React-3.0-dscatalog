package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerNameDTO;
import com.devsuperior.uri2602.projections.CustomerNameProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner{

	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	
	// executa este comando logo apos inicializar
	@Override
	public void run(String... args) throws Exception {
		System.out.println("INICIANDO O TESTE");
		
		//SQL
		List<CustomerNameProjection> list = repository.search1("RS");
		
		// converte a projection em DTO
		List<CustomerNameDTO> result1 = list.stream().map(x -> new CustomerNameDTO(x)).collect(Collectors.toList());
		
		for (CustomerNameDTO obj : result1) {
			System.out.println(obj);
		}
		
	}
}
