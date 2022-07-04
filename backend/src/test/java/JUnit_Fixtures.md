# Fixtures
é uma forma de organizar melhor o codigo dos testes e evitar repetiçoes

| JUnit 5    	| Junit 4      	| Objetivo                                                         	|
|------------	|--------------	|------------------------------------------------------------------	|
| @BeforeAll 	| @BeforeClass 	| Preparaçao antes de todos os testes da classe (metodo estatico)  	|
| @AfterAll  	| AfterClass   	| Preparaçao depois de todos os testes da classe (metodo estatico) 	|
| @BeforeAll 	| @Before      	| Preparaçao antes de cada teste da classe                         	|
| @AfterAll  	| @After       	| Preparaçao depois de cada teste da classe                        	|