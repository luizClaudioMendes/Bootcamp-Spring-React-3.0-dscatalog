package com.devsuperior.dscatalog.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatalog.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	private long existingId;
	private long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = -1L;
		
		//comportamento simulado para metodos sem retorno
		Mockito.doNothing().when(repository).deleteById(existingId);
		
		//comportamento simulado quando queremos lançar uma exception no mock
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
	}
	
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		//verifica se o metodo deleteById foi chamado pelo teste
		Mockito.verify(repository).deleteById(existingId);
		//verifica se o metod deleteById foi chamado 2 vezes
		verify(repository, Mockito.times(1)).deleteById(existingId);
	}
}
