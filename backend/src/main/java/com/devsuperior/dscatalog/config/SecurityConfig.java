package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	// ESTA CONFIGURAÇAO PERMITE QUE OS ENDPOINTS FIQUEM ABERTOS APOS A INCLUSAO DA DEPENDENCIA DO SECURITY
	// NAO EXIGINDO O LOGIN
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");
	}
}