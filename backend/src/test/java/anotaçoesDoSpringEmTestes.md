# Anotaçoes usadas nas classes de teste

## @SpringBootTest

@SpringBootTest
carrega o contexto da aplicação completo (sobe o sistema todo)

- usado em testes de integraçao


## @SpringBootTest & @AutoConfigureMockMvc

@SpringBootTest
@AutoConfigureMockMvc
carrega o contexto da aplicaçao mas somente trata as requisiçoes, sem subir o servidor

- usado em testes de integraçao e web


## @WebMvcTest(Classe.class)

@WebMvcTest(NomeDaClasseASerTestada.class)
carrega o contexto, porem somente da camada web

- usado em testes de unidade das controllers/resources


## @ExtendWith(SprongExtension.class)

@ExtendWith(SpringExtention.class)
nao carrega o contexto, mas permite usar os recursos do spring com JUnit

- usado em testes de unidade das services/components


## @DataJpaTest

@DataJpaTest
carrega somente os componentes relacionados ao Spring Data JPA.
cada teste é transacional e da rollback ao final.

- usado em testes de unidade das repositories

 
