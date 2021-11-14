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
import org.springframework.security.crypto.password.PasswordEncoder;

import education.org.main.filter.CustomerAuthenticationFilter;
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
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/etudiants/**").hasAnyAuthority("USER");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new CustomerAuthenticationFilter(authenticationManagerBean()));
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
	    
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//	    return bCryptPasswordEncoder;
//	    }
  
	

	
} 
