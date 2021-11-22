package education.org.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import education.org.main.dao.UtilisateurRepository;
import education.org.main.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import education.org.main.dao.FiliereRepository;
import education.org.main.dao.PromotionRepository;
import education.org.main.services.EtudiantService;
import education.org.main.services.RoleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@EnableConfigurationProperties(EducationMainApplication.PrefixlessProperties.class)
@SpringBootApplication
public class EducationMainApplication extends SpringBootServletInitializer implements CommandLineRunner {

//	public static void main(String[] args) {
//		new SpringApplicationBuilder(EducationMainApplication.class)
//		.properties(Collections.singletonMap("", "value"))
//		.run(args);
////		ConfigurableApplicationContext applicationContext = SpringApplication.run(EducationMainApplication.class,
////				args);
//	}
//
//	@ConfigurationProperties
//	public static class PrefixlessProperties {
//
//	}
//
//	@Bean
//	PasswordEncoder passwordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	CommandLineRunner run(EtudiantService etudServ, RoleService roleService, PromotionRepository promotionRepository, FiliereRepository filiereRepository)
//	{
//		return args->{
//
//
//			//############################## Rules ########################################
//			//Rules
//			Role USER = new Role("ROLE_USER", "can read course");
//			Role ADMIN_MANAGER = new Role("ROLE_ADMIN_MANAGER", "can manage operation");
//			Role ADMIN_PROFFESSEUR = new Role("ROLE_ADMIN_PROFFESSEUR", "can manage courses");
//			Role ADMIN_GENERAL = new Role("ROLE_ADMIN_GENERAL", "manage all application");
//			Role ANONYMOUS = new Role("ROLE_ANONYMOUS", "can only acces to landin page");
//			Role CUSTOMER_USER = new Role("ROLE_CUSTOMER_USER", "private access");
//
//			// save roles
//						roleService.save(USER);
//						roleService.save(ADMIN_MANAGER);
//						roleService.save(ADMIN_PROFFESSEUR);
//						roleService.save(ADMIN_GENERAL);
//						roleService.save(ANONYMOUS);
//						roleService.save(CUSTOMER_USER);
//			//############################## Promotion -> Etudiant ########################################
//
//			LocalDate today= LocalDate.now();
//			LocalDate plus90days = today.plusDays(90);
//			Promotion Semestre1 = new Promotion("1° Semestre" ,"3 mois " , today, plus90days, 300.0, new ArrayList<>());
//			Promotion Semestre2 = new Promotion("2° Semestre" ,"6 mois " , today, plus90days, 600.0, new ArrayList<>());
//						//Save Promotion
//						promotionRepository.save(Semestre1);
//						promotionRepository.save(Semestre2);
//			//############################## Filieres ########################################
//			Filiere Math = new Filiere("2° Bac sience physique", "tous les cours du math", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
//			Filiere Physique = new Filiere("2° Bac sience de la vie et terre", "tous les cours du physique", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
//			Filiere SVT = new Filiere("2° Bac Science Economie", "tous les cours du svt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
//						//Save Filiere
//						filiereRepository.save(Math);
//						filiereRepository.save(SVT);
//						filiereRepository.save(Physique);
//			//############################## Etudiant ########################################
//
//			//Etudiants
//			Etudiant Brahim = new Etudiant("EL BANAJI", "Brahim", "065343424", "banaji@gmail.com","brahim" ,"12340987", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//			Etudiant Hamza = new Etudiant("EL BANAJI", "Hamza", "065343424", "Hamza@gmail.com", "Hamza","1234", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//			Etudiant Karim = new Etudiant("EL BANAJI", "Karim", "065343424", "Karim@gmail.com", "Karim","5678", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//			Etudiant khawla = new Etudiant("EL BANAJI", "khawla", "065343424", "khawla@gmail.com", "khawla","9123", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//			Etudiant hakim = new Etudiant("EL BANAJI", "hakim", "065343424", "hakim@gmail.com", "hakim","4567", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//			Etudiant khalid = new Etudiant("EL BANAJI", "khalid", "065343424", "khalid@gmail.com", "khalid","8901", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//			Etudiant yassin = new Etudiant("EL BANAJI", "yassin", "065343424", "yassin@gmail.com", "yassin","2345", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//			Etudiant mostapha = new Etudiant("EL BANAJI", "mostapha", "065343424", "mostapha@gmail.com", "mostapha","6789", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//						//save etudiants
//						etudServ.save(Brahim);
//						etudServ.save(Hamza);
//						etudServ.save(Karim);
//						etudServ.save(khawla);
//						etudServ.save(hakim);
//						etudServ.save(khalid);
//						etudServ.save(yassin);
//						etudServ.save(mostapha);
//		//############################## Role->Etudiant ########################################
//						etudServ.addRoleToEtudiant(Brahim, USER);
//						etudServ.addRoleToEtudiant(Hamza, ADMIN_GENERAL); etudServ.addRoleToEtudiant(Hamza, ADMIN_MANAGER);
//						etudServ.addRoleToEtudiant(yassin, ADMIN_PROFFESSEUR);
//						etudServ.addRoleToEtudiant(khawla, ANONYMOUS);
//						etudServ.addRoleToEtudiant(Karim, CUSTOMER_USER);
//						etudServ.addRoleToEtudiant(khalid, ANONYMOUS);
//		//############################## Promotion->Etudiant ########################################
//						etudServ.addPromotionToEtudiant(Brahim, Semestre1);
//						etudServ.addPromotionToEtudiant(Brahim, Semestre2);
//						etudServ.addPromotionToEtudiant(Hamza, Semestre1);
//						etudServ.addPromotionToEtudiant(yassin, Semestre2);
//		//############################## Filiere->Etudiant ########################################
//						etudServ.addFiliereToEtudiant(Brahim, Math);
//						etudServ.addFiliereToEtudiant(Hamza, Physique);
//						etudServ.addFiliereToEtudiant(yassin, SVT);
//		//############################## Etudiant ########################################
//		//############################## Etudiant ########################################
//		//############################## Etudiant ########################################
//		};
//	}

	@Autowired
	private UtilisateurRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(EducationMainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		repositoryRestConfiguration.getCorsRegistry()
//		.addMapping("/**")
//		.allowedOrigins("http://localhost:4200")
//		.allowedHeaders("*")
//		.allowedMethods("OPTIONS","PUT","POST","PATCH","HEAD","GET","DELETE");

//		clientDomaineRepository.save(new Client("gallouche", "hamza", "ainchock",
//		"photo", "hamza8gallouche@gmail.com", "0611350528",0 ,"societe1"));
//		clientDomaineRepository.save(new Client("elMaakoul", "ayoub", "ainchock",
//				"photo", "ayoub.elmakoul18@gmail.com", "0666642967",0 ,"societe2"));
//		clientDomaineRepository.save(new Client("el bannaji", "brahim", "tetouan",
//				"photo", "brahim.bannaji@gmail.com", "0766335522",0 ,"societe3"));
//
//		System.out.println("hello from commandLineRunner");

//		catDomaineRepository.save(new Categorie("Aluminium", "Categorie qui contient les barres aluminium"));
//		catDomaineRepository.save(new Categorie("Vitre", "Categorie qui contient les Vitres à Vendre"));
//

//		long id1 = 14;
//		Optional<Categorie> cat1 = catDomaineRepository.findById(id1);
//		long id2 = 15;
//		Optional<Categorie> cat2 = catDomaineRepository.findById(id2);
//
//		sousCatDomaineRepository.save(new SousCategorie("Accessoires", "sous categorie des accessoires Aluminium",cat1.get()));
//		sousCatDomaineRepository.save(new SousCategorie("Barres", "sous categorie des Barres Aluminium",cat2.get()));
//
//		long id = 19;
//		Optional<SousCategorie> sousCat = sousCatDomaineRepository.findById(id);
//		produitDomaineRepository.save(new Produit("Aluma7000", sousCat.get()));

//		long id = 23;
//		Optional<Produit> pr = produitDomaineRepository.findById(id);
//		articleDomaineRepository.save(new Article("article 1 ", 6, 8, "marron", 220,
//				20, 320, pr.get()));

		//frsDomaineRepository.save(new Fournisseur("Alumesk System", "sidi maarouf", "photo", "0661979443", "alumesk@gmail.com", 0));
		// Utilisateur(String username, String password, String roles, String permissions,
		// String fullName)

		Utilisateur superAdmin = this.userRepository.findByUsername("superAdmin");
		if (superAdmin == null) {
			superAdmin = new Utilisateur("superAdmin",passwordEncoder.encode("superAdmin"),"ADMIN","ALL",
					"hamza.gallouhe@gmail.com", "Hamza Gallouche");
			this.userRepository.save(superAdmin);
		}

	}
}


