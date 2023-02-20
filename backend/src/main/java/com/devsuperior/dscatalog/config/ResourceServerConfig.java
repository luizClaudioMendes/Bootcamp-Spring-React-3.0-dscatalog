package com.devsuperior.dscatalog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer // esta anotaçao vai processar para que esta classe implemente a funcionalidade do oauth2
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Value("${cors.origins}")
	private String corsOrigins;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
	
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
		
		// H2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable(); // o H2 requer que sejam desabilitados os frames
		}
		
		// rotas liberadas
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() // qualquer url listada no PUBLIC
		.antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll() // quaquer metodo get do array do OPERATOR_OR_ADMIN esta liberado
		.antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR", "ADMIN") // qualquer URL em OPERATOR OR ADMIN OBS:perfil cadastrado no banco, sem a palavra 'ROLE'
		.antMatchers(ADMIN).hasRole("ADMIN") // qualquer URL em ADMIN deve ter o papel de ADMIN
		.anyRequest().authenticated(); // qualquer outra rota deve estar logado
		
		http.cors().configurationSource(corsConfigurationSource());
	}
		
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		String[] origins = corsOrigins.split(",");

	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOriginPatterns(Arrays.asList(origins));
	    corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
	    corsConfig.setAllowCredentials(true);
	    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	 
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);
	    return source;
	}
	

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
	    FilterRegistrationBean<CorsFilter> bean
	            = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
}
