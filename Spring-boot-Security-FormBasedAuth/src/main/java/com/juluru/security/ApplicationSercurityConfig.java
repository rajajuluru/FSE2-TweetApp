package com.juluru.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.thymeleaf.cache.AlwaysValidCacheEntryValidity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ApplicationSercurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers( "/css/*", "/","/javascript/*").permitAll()
		//.antMatchers("/hello")
		//.hasRole("ADMIN")
		.anyRequest()
				.authenticated().and().formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/hello",true).and()
				.logout().logoutUrl("/logout").clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
				//.logoutSuccessUrl("/logoutSuccess");
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() { // is how u retrieve
														// users from DB
		// TODO Auto-generated method stub
		// return super.userDetailsService();
		 List<SimpleGrantedAuthority> READAUTHORITY = new ArrayList<>();
		 READAUTHORITY.add(new SimpleGrantedAuthority("API-READ"));
		 List<SimpleGrantedAuthority> WRITEAUTHORITY = new ArrayList<>();
		 WRITEAUTHORITY.add(new SimpleGrantedAuthority("API-READ"));
		 WRITEAUTHORITY.add(new SimpleGrantedAuthority("API-WRITE"));
		 
		UserDetails raja = User.builder().username("raja")
				.password(passwordEncoder.encode("raja"))
				.roles(ApplicationUserRole.STUDENT.name())
				//.authorities(READAUTHORITY)
				.build();
		
		UserDetails admin = User.builder().username("admin")
				.password(passwordEncoder.encode("admin"))
				//.authorities(WRITEAUTHORITY)
				.roles(ApplicationUserRole.ADMIN.name())
				.build();
		
		UserDetails tom = User.builder().username("tom")
				.password(passwordEncoder.encode("tom"))
				//.authorities(WRITEAUTHORITY)
				.roles(ApplicationUserRole.ADMINTRAINEE.name(),ApplicationUserRole.ADMIN.name())
				.build();
		
		//.password("password").roles("admin").build();
		return new InMemoryUserDetailsManager(raja,admin,tom);
	}
	
	
	
	/*
	   @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(daoAuthenticationProvider());
	    }
*/
	   /* @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setPasswordEncoder(passwordEncoder);
	        provider.setUserDetailsService(applicationUserService);
	        return provider;
	    }*/

}
