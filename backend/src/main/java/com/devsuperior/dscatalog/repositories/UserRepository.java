package com.devsuperior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email); 
	// seguindo esta sintaxe, somente com isso conseguimos implementar uma busca pelo 
	// spring JPA para uma query customizada passando o where no argumento
	// estas consultas simples se chamam de query methods.
	// mais info na doc: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	// ou buscar por spring jpa query methods
	
}
