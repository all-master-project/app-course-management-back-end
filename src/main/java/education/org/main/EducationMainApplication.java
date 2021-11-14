package education.org.main;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import education.org.main.entities.Etudiant;
import education.org.main.entities.Role;
import education.org.main.services.EtudiantService;
import education.org.main.services.RoleService;

@EnableConfigurationProperties(EducationMainApplication.PrefixlessProperties.class)
@SpringBootApplication
public class EducationMainApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EducationMainApplication.class)
		.properties(Collections.singletonMap("", "value"))
		.run(args);
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(EducationMainApplication.class,
//				args);
	}
	
	@ConfigurationProperties
	public static class PrefixlessProperties {

	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{ 
		return new BCryptPasswordEncoder();
	}  
	 
	@Bean
	CommandLineRunner run(EtudiantService etudServ, RoleService roleService) 
	{
		return args->{
			
			 
			//Rules
			Role USER = new Role("User", "can read course");
			Role ADMIN_MANAGER = new Role("ADMIN_MANAGER", "can manage operation");
			Role ADMIN_PROFFESSEUR = new Role("ADMIN_PROFFESSEUR", "can manage courses");
			Role ADMIN_GENERAL = new Role("ADMIN_GENERAL", "manage all application");
			Role ANONYMOUS = new Role("ANONYMOUS", "can only acces to landin page");
			Role CUSTOMER_USER = new Role("CUSTOMER_USER", "private access");
			 
			//Etudiants
			Etudiant Brahim = new Etudiant("EL BANAJI", "Brahim", "065343424", "banaji@gmail.com","brahim" ,"12340987", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			 
			Etudiant Hamza = new Etudiant("EL BANAJI", "Hamza", "065343424", "Hamza@gmail.com", "Hamza","1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant Karim = new Etudiant("EL BANAJI", "Karim", "065343424", "Karim@gmail.com", "Karim","5678", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant khawla = new Etudiant("EL BANAJI", "khawla", "065343424", "khawla@gmail.com", "khawla","9123", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant Farid = new Etudiant("EL BANAJI", "Farid", "065343424", "hakim@gmail.com", "hakim","4567", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant khalid = new Etudiant("EL BANAJI", "khalid", "065343424", "khalid@gmail.com", "khalid","8901", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant yassin = new Etudiant("EL BANAJI", "yassin", "065343424", "yassin@gmail.com", "yassin","2345", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant mostapha = new Etudiant("EL BANAJI", "mostapha", "065343424", "mostapha@gmail.com", "mostapha","6789", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			
			/*
			 * *
			//Save Entities 
			           // save roles 
						roleService.save(USER);
						roleService.save(ADMIN_MANAGER);
						roleService.save(ADMIN_PROFFESSEUR);
						roleService.save(ADMIN_GENERAL); 
						roleService.save(ANONYMOUS);
						roleService.save(CUSTOMER_USER);
						
						//save etudiants
						etudServ.save(Brahim);
						etudServ.save(Hamza);
						etudServ.save(Karim);
						etudServ.save(khawla);
						etudServ.save(Farid);
						etudServ.save(khalid);
						etudServ.save(yassin);
			 
			// relashion 
			Brahim.getRoles().add(ANONYMOUS);
			Hamza.getRoles().add(ADMIN_GENERAL);  
			Hamza.getRoles().add(ADMIN_PROFFESSEUR);   
			Hamza.getRoles().add(ADMIN_MANAGER);
			Karim.getRoles().add(USER);
			khawla.getRoles().add(USER);
			Farid.getRoles().add(USER);
			khalid.getRoles().add(ANONYMOUS);
			yassin.getRoles().add(CUSTOMER_USER);
			yassin.getRoles().add(USER);
			mostapha.getRoles().add(USER);
			 * 
			 */
		};
	}
}
