package education.org.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import education.org.main.dao.FiliereRepository;
import education.org.main.dao.PromotionRepository;
import education.org.main.entities.Etudiant;
import education.org.main.entities.Filiere;
import education.org.main.entities.Promotion;
import education.org.main.entities.Role;
import education.org.main.services.EtudiantService;
import education.org.main.services.RoleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	CommandLineRunner run(EtudiantService etudServ, RoleService roleService, PromotionRepository promotionRepository, FiliereRepository filiereRepository) 
	{
		return args->{

			
			//############################## Rules ########################################
			//Rules
			Role USER = new Role("ROLE_USER", "can read course");
			Role ADMIN_MANAGER = new Role("ROLE_ADMIN_MANAGER", "can manage operation");
			Role ADMIN_PROFFESSEUR = new Role("ROLE_ADMIN_PROFFESSEUR", "can manage courses");
			Role ADMIN_GENERAL = new Role("ROLE_ADMIN_GENERAL", "manage all application");
			Role ANONYMOUS = new Role("ROLE_ANONYMOUS", "can only acces to landin page");
			Role CUSTOMER_USER = new Role("ROLE_CUSTOMER_USER", "private access");
			
			// save roles 
						roleService.save(USER);
						roleService.save(ADMIN_MANAGER);
						roleService.save(ADMIN_PROFFESSEUR);
						roleService.save(ADMIN_GENERAL); 
						roleService.save(ANONYMOUS); 
						roleService.save(CUSTOMER_USER);
			//############################## Promotion -> Etudiant ########################################
			 
			LocalDate today= LocalDate.now();
			LocalDate plus90days = today.plusDays(90);
			Promotion Semestre1 = new Promotion("1° Semestre" ,"3 mois " , today, plus90days, 300.0, new ArrayList<>());
			Promotion Semestre2 = new Promotion("2° Semestre" ,"6 mois " , today, plus90days, 600.0, new ArrayList<>());
						//Save Promotion
						promotionRepository.save(Semestre1);
						promotionRepository.save(Semestre2);
			//############################## Filieres ########################################
			Filiere Math = new Filiere("2° Bac sience physique", "tous les cours du math", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
			Filiere Physique = new Filiere("2° Bac sience de la vie et terre", "tous les cours du physique", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
			Filiere SVT = new Filiere("2° Bac Science Economie", "tous les cours du svt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
						//Save Filiere
						filiereRepository.save(Math);
						filiereRepository.save(SVT);
						filiereRepository.save(Physique);
			//############################## Etudiant ########################################
			
			//Etudiants 
			Etudiant Brahim = new Etudiant("EL BANAJI", "Brahim", "065343424", "banaji@gmail.com","brahim" ,"12340987", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant Hamza = new Etudiant("EL BANAJI", "Hamza", "065343424", "Hamza@gmail.com", "Hamza","1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant Karim = new Etudiant("EL BANAJI", "Karim", "065343424", "Karim@gmail.com", "Karim","5678", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant khawla = new Etudiant("EL BANAJI", "khawla", "065343424", "khawla@gmail.com", "khawla","9123", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant hakim = new Etudiant("EL BANAJI", "hakim", "065343424", "hakim@gmail.com", "hakim","4567", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant khalid = new Etudiant("EL BANAJI", "khalid", "065343424", "khalid@gmail.com", "khalid","8901", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant yassin = new Etudiant("EL BANAJI", "yassin", "065343424", "yassin@gmail.com", "yassin","2345", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Etudiant mostapha = new Etudiant("EL BANAJI", "mostapha", "065343424", "mostapha@gmail.com", "mostapha","6789", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
						//save etudiants
						etudServ.save(Brahim);
						etudServ.save(Hamza); 
						etudServ.save(Karim);
						etudServ.save(khawla); 
						etudServ.save(hakim);  
						etudServ.save(khalid);
						etudServ.save(yassin);
						etudServ.save(mostapha);
		//############################## Role->Etudiant ########################################
						etudServ.addRoleToEtudiant(Brahim, USER);
						etudServ.addRoleToEtudiant(Hamza, ADMIN_GENERAL); etudServ.addRoleToEtudiant(Hamza, ADMIN_MANAGER);
						etudServ.addRoleToEtudiant(yassin, ADMIN_PROFFESSEUR);
						etudServ.addRoleToEtudiant(khawla, ANONYMOUS);
						etudServ.addRoleToEtudiant(Karim, CUSTOMER_USER);
						etudServ.addRoleToEtudiant(khalid, ANONYMOUS);
		//############################## Promotion->Etudiant ########################################
						etudServ.addPromotionToEtudiant(Brahim, Semestre1);
						etudServ.addPromotionToEtudiant(Brahim, Semestre2);
						etudServ.addPromotionToEtudiant(Hamza, Semestre1);
						etudServ.addPromotionToEtudiant(yassin, Semestre2);
		//############################## Filiere->Etudiant ########################################
						etudServ.addFiliereToEtudiant(Brahim, Math);
						etudServ.addFiliereToEtudiant(Hamza, Physique);
						etudServ.addFiliereToEtudiant(yassin, SVT);
		//############################## Etudiant ########################################
		//############################## Etudiant ########################################
		//############################## Etudiant ########################################
						
									 
 
			 
		
		}; 
	}
}


