package com.devsuperior.dscatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// ESTA CONFIGURAÇAO PERMITE QUE OS ENDPOINTS FIQUEM ABERTOS APOS A INCLUSAO DA DEPENDENCIA DO SECURITY
	// NAO EXIGINDO O LOGIN
	@Override
	public void configure(WebSecurity web) throws Exception {
		// web.ignoring().antMatchers("/**");
		// ESTA CONFIGURAÇAO PERMITE QUE OS ENDPOINTS FIQUEM ABERTOS APOS A INCLUSAO DA DEPENDENCIA DO SECURITY
		// NAO EXIGINDO O LOGIN
		
		web.ignoring().antMatchers("/actuator/**"); //spring cloud liberando acessos
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
		
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	
}