package com.devsuperior.dscatalog.repositories;

import java.util.List;

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
			//+ "(:category IS NULL OR :category IN cats ) AND " // isto nao funciona no postgres
			+ "(COALESCE(:categories) IS NULL OR cats IN :categories ) AND " // postgres
			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%')) )") 
	Page<Product> find(List<Category> categories, String name, Pageable pageable);
	
	
	// to resolve n + 1 queries and get products with categories
	@Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj IN :products")
	List<Product> findProductsWithCategories(List<Product> products);

}
