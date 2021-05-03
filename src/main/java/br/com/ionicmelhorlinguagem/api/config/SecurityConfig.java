package br.com.ionicmelhorlinguagem.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import br.com.ionicmelhorlinguagem.api.security.JwtConfigurer;
import br.com.ionicmelhorlinguagem.api.security.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable()
			.csrf().disable().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/actuator/health").permitAll()
			.antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/v1/**").hasRole("USER")
			.anyRequest().authenticated().and().apply(new JwtConfigurer(jwtTokenProvider));
	}
}