package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// return new DelegatingPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.csrf().disable().authorizeRequests()
				.antMatchers("/error", "/login/**", "/js/**", "/css/**", "/image/**", "/webjars/**").permitAll()
				// REST controllers
				.antMatchers("/clientes", "/agencias", "/pacotes").permitAll() // REST Controllers (GET - get All, POST - Create) 
				.antMatchers("/clientes/{\\d+}", "/agencias/{\\d+}").permitAll() // REST Controllers (GET by id, PUT - Update by id, DELETE by ID)
				.antMatchers("/pacotes/agencias/{\\d+}").permitAll() // REST Controllers (GET by id da agencia)
				.antMatchers("/pacotes/clientes/{\\d+}").permitAll() // REST Controllers (GET by id do cliente)
				.antMatchers("/pacotes/destinos/{\\w+}").permitAll() // REST Controllers (GET by destino)
				// Other controllers
				.antMatchers("/compras/**").hasAuthority("CLIENTE")
				.antMatchers("/clientes/**", "/agencias/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}
}