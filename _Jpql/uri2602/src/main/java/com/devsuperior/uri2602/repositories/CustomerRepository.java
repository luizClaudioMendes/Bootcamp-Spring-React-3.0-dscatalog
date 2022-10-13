package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.dto.CustomerNameDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerNameProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// SQL
	@Query(nativeQuery = true, value = "SELECT name FROM customers WHERE UPPER(state) = UPPER(:state) ")
	List<CustomerNameProjection> search1(String state);

	// JPQL - o nome da tabela é o nome da classe e tem que dar um apelido
	@Query("SELECT new com.devsuperior.uri2602.dto.CustomerNameDTO(obj.name) FROM Customer obj WHERE UPPER(obj.state) = UPPER(:state) ")
	List<CustomerNameDTO> search2(String state);
}
