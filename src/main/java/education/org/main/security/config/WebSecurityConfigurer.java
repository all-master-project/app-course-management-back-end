package education.org.main.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication() 
			.withUser("ADMIN").password(passwordEncoder().encode("admin1234")).roles("ADMIN")
			.and()
			.withUser("USER").password(passwordEncoder().encode("user1234")).roles("USER");
			
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
} 
