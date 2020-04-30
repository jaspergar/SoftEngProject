package io.jg_intelligence.security;



import static io.jg_intelligence.security.SecurityConstants.H2_URL;
import static io.jg_intelligence.security.SecurityConstants.SIGN_UP_URLS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jg_intelligence.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	JwtAunthenticationEntryPoint jwtAunthenticationEntryPoint;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {return new JwtAuthenticationFilter();}
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(jwtAunthenticationEntryPoint).and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.headers().frameOptions().sameOrigin()
		.and()
		.authorizeRequests()
		.antMatchers("/",
				"/favicon.ico",
				"/**/*.png",
				"/**/*.gif",
				"/**/*.svg",
				"/**/*.jpg",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js").permitAll().antMatchers(SIGN_UP_URLS).permitAll()
		.antMatchers(H2_URL).permitAll()
//		.antMatchers("/api/subject/**").permitAll()
		.anyRequest().authenticated();
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
}
