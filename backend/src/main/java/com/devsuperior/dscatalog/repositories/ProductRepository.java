package com.devsuperior.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats WHERE "// como a restriçao das categorias é de um para muitos é preciso fazer o INNER JOIN. se fosse de um para um nao é necessario
			+ "(:category IS NULL OR :category IN cats )") 
	Page<Product> find(Category category, Pageable pageable);

}
