package com.ssafy.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.service.AccountServiceImpl;

// https://xmfpes.github.io/spring/spring-security/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	AccountServiceImpl accountService;

	@Autowired
	public SecurityConfig(AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.anyRequest().permitAll()
//		.antMatchers("/**").permitAll()
//		.and().formLogin()
//		.loginPage("/login")
//		.loginProcessingUrl("/login")
//		.defaultSuccessUrl("/") // 설공시, URL 맵핑
//    	.failureUrl("/login") // 실패시, URL 맵핑
//    	.and()
//    	.logout();

		http.csrf().disable().authorizeRequests().anyRequest().permitAll()
		.and()
		.formLogin().loginPage("/")
				.defaultSuccessUrl("/")
				.and()
				.logout()
				.logoutUrl("/logout");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// UserDetailsService를 이용해 로그인 인증 처리를 해주어야 한다.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
	}
}