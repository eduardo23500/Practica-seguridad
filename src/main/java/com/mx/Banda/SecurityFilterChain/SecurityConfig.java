package com.mx.Banda.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity


public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		
		return httpSecurity
				.csrf().disable()
					.authorizeHttpRequests(auth->{
						auth.requestMatchers("/Bandas/guardar").permitAll();
						auth.requestMatchers("/Bandas/editar").permitAll();
						auth.requestMatchers("/Bandas/eliminar").permitAll();
						auth.requestMatchers("/Bandas/buscar").permitAll();
						auth.anyRequest().authenticated();
					})
					.formLogin().permitAll()
					.successHandler(successHandler()).permitAll()
				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.invalidSessionUrl("/login")
					.maximumSessions(1)
					.expiredUrl("/login")
					.and()
				.and()
				.build();
	}
	
	public AuthenticationSuccessHandler successHandler() {
		return ((request,response,authenticated) ->{
			response.sendRedirect("/Bandas/mostrar");
		});
	}
	
}
