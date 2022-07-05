package com.devsuperior.dscatalog.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Mock
	private CategoryRepository categoryRepository;

	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private PageImpl<Product> page; // PageImpl usado em testes
	private Product product;
	private Category category;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = -1L;
		dependentId = 4L;
		product = Factory.createProduct();
		page = new PageImpl<>(List.of(product));
		category = Factory.createCategory();

		// comportamento simulado para metodos sem retorno
		Mockito.doNothing().when(repository).deleteById(existingId);

		// comportamento simulado quando queremos lançar uma exception no mock
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);

		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

		// quando um metodo tem retorno a sintaxe é ao contrario (when/do)
		// retorno de uma pagina de dados
		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Mockito.when(repository.getOne(existingId)).thenReturn(product);
		Mockito.when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

		Mockito.when(categoryRepository.getOne(1L)).thenReturn(category);

	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});

		// verifica se o metodo deleteById foi chamado pelo teste
		Mockito.verify(repository).deleteById(existingId);
		// verifica se o metod deleteById foi chamado 2 vezes
		verify(repository, Mockito.times(1)).deleteById(existingId);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});

		Mockito.verify(repository).deleteById(nonExistingId);
		verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionExceptionWhenDependentId() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});

		Mockito.verify(repository).deleteById(dependentId);
		verify(repository, Mockito.times(1)).deleteById(dependentId);
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<ProductDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
	}

	@Test
	public void findByIdShouldReturnProduct() {

		ProductDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(existingId, result.getId());
		Mockito.verify(repository, Mockito.times(1)).findById(existingId);
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});

		Mockito.verify(repository).findById(nonExistingId);
		verify(repository, Mockito.times(1)).findById(nonExistingId);
	}

	@Test
	public void updateShouldUpdateAndReturnProduct() {

		ProductDTO initial = Factory.createProductDTO();
		initial.setName("Teste");
		ProductDTO result = service.update(existingId, initial);
		Assertions.assertEquals(existingId, result.getId());
		Assertions.assertEquals("Teste", result.getName());
		Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
		Mockito.verify(repository, Mockito.times(1)).getOne(existingId);
		Mockito.verify(categoryRepository, Mockito.times(1)).getOne(ArgumentMatchers.any());

	}

	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		ProductDTO initial = Factory.createProductDTO();
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, initial);
		});
		
		Mockito.verify(repository, Mockito.times(1)).getOne(nonExistingId);
	}
}
