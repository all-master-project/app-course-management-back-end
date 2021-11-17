package education.org.main.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import education.org.main.filter.CustomerAuthenticationFilter;
import education.org.main.filter.CustomerAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@Configuration 
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private  UserDetailsService userDetailsService;
	
	public WebSecurityConfigurer(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure(AuthenticationManagerBuilder auth){auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());}");
		auth.userDetailsService(userDetailsService)
	   .passwordEncoder(new BCryptPasswordEncoder());
	}  
	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		CustomerAuthenticationFilter customerAuthenticationFilter = new CustomerAuthenticationFilter(authenticationManagerBean());
//		customerAuthenticationFilter.setFilterProcessesUrl("/api/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login","/api/etudiants/refresh/token/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/etudiants/**","/api/etudiants/refresh/token").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/etudiants/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new CustomerAuthenticationFilter(authenticationManagerBean()));
		http.addFilterBefore(new CustomerAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	} 
	
	@Bean 
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public UserDetailsService getUserDetailsService() {
	    return userDetailsService;
	    }

	    @Autowired
	    public void setUserDetailsService(UserDetailsService userDetailsService) {
	    this.userDetailsService = userDetailsService;
	    }
} 
