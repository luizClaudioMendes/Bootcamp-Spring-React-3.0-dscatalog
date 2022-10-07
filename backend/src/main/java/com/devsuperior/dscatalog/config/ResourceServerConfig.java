package com.devsuperior.dscatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer // esta anotaçao vai processar para que esta classe implemente a funcionalidade do oauth2
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = {"/oauth/token"};
	
	private static final String[] OPERATOR_OR_ADMIN = {"/products/**", "/categories/**"};
	
	private static final String[] ADMIN = {"/users/**"};
	
	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore); // com esta linha o server é capaz de decodificar o token recebido para valida-lo
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/* 
		 * uso no curso:
		 * o catalogo é liberado para todo mundo, sem necessidade de login
		 * os cruds precisam de login
		 * * os produtos e categorias precisam somente do OPERADOR E ADMIN
		 * * usuario somente ADMIN
		 */
		
		// rotas liberadas
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() // qualquer url listada no PUBLIC
		.antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll() // quaquer metodo get do array do OPERATOR_OR_ADMIN esta liberado
		.antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR", "ADMIN") // qualquer URL em OPERATOR OR ADMIN OBS:perfil cadastrado no banco, sem a palavra 'ROLE'
		.antMatchers(ADMIN).hasRole("ADMIN") // qualquer URL em ADMIN deve ter o papel de ADMIN
		.anyRequest().authenticated(); // qualquer outra rota deve estar logado
		
		
		
	}
	

}
