package education.org.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import education.org.main.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@EnableConfigurationProperties(EducationMainApplication.PrefixlessProperties.class)
@SpringBootApplication
public class EducationMainApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	PromotionRepository promotionRepository;

	@Autowired
	FiliereRepository filiereRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	EtudiantService etudiantService;

	public static void main(String[] args) {
		SpringApplication.run(EducationMainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//############################## Rules ################################################################################
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
					
		//############################## Utilisateur ################################################################################
		Utilisateur BrahimUser = new Utilisateur("Brahim","1234", 1,new ArrayList<>() ,"elbanaji.brahim@gmail.com", "0661123254");
		Utilisateur HamzaUser = new Utilisateur("Hamza","1234", 1,new ArrayList<>() ,"hamza.galouchhe@gmail.com", "0645121254");
		Utilisateur YassinUser = new Utilisateur("Yassine","1234", 1,new ArrayList<>() ,"yassine.gallouche@gmail.com", "0660324324");
					// save roles
					utilisateurService.save(BrahimUser);
					utilisateurService.save(HamzaUser);
					utilisateurService.save(YassinUser);
		
		//############################## Etudiant ################################################################################
		Etudiant Brahim = new Etudiant("EL BANAJI", "Brahim",new Utilisateur(), new ArrayList<>(), new ArrayList<>());
		Etudiant Hamza = new Etudiant("EL BANAJI", "Hamza",new Utilisateur(), new ArrayList<>(), new ArrayList<>());
		Etudiant yassin = new Etudiant("EL BANAJI", "yassin",new Utilisateur(), new ArrayList<>(), new ArrayList<>());
					//save etudiants
					etudiantService.save(Brahim);
					etudiantService.save(Hamza);
					etudiantService.save(yassin);
		//############################## Promotion -> Etudiant ################################################################################
		LocalDate today= LocalDate.now();
		LocalDate plus90days = today.plusDays(90);
		Promotion Semestre1 = new Promotion("1° Semestre" ,"3 mois " , today, plus90days, 300.0, new ArrayList<>());
		Promotion Semestre2 = new Promotion("2° Semestre" ,"6 mois " , today, plus90days, 600.0, new ArrayList<>());
					//Save Promotion
					promotionRepository.save(Semestre1);
					promotionRepository.save(Semestre2);
//		//############################## Filieres ########################################
		Filiere Math = new Filiere("2° Bac sience physique", "tous les cours du math", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
		Filiere Physique = new Filiere("2° Bac sience de la vie et terre", "tous les cours du physique", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
		Filiere SVT = new Filiere("2° Bac Science Economie", "tous les cours du svt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() );
					//Save Filiere
					filiereRepository.save(Math);
					filiereRepository.save(SVT);
					filiereRepository.save(Physique);
					
		//############################## Role->Utlisateur ################################################################################
		utilisateurService.addRoleToUser(BrahimUser, USER);
		utilisateurService.addRoleToUser(HamzaUser, ADMIN_GENERAL);
		utilisateurService.addRoleToUser(HamzaUser, ADMIN_MANAGER);
		utilisateurService.addRoleToUser(HamzaUser, ADMIN_PROFFESSEUR);
		utilisateurService.addRoleToUser(HamzaUser, USER);
		utilisateurService.addRoleToUser(YassinUser, USER);
		
		//################################# Promotion -> Etudiant ###########################################################
		etudiantService.addUserAssociationToEtudiant(Brahim, BrahimUser);
		etudiantService.addUserAssociationToEtudiant(Hamza, HamzaUser);
		etudiantService.addUserAssociationToEtudiant(yassin, YassinUser);
		
		//################################# Promotion -> Etudiant ###########################################################
					etudiantService.addPromotionToEtudiant(Brahim, Semestre1); etudiantService.addPromotionToEtudiant(Brahim, Semestre2);
					etudiantService.addPromotionToEtudiant(Hamza,Semestre1);
					etudiantService.addPromotionToEtudiant(yassin, Semestre2);
					
		//################################# Filiere -> Etudiant ###########################################################
					etudiantService.addFiliereToEtudiant(Brahim, Math);	etudiantService.addFiliereToEtudiant(Brahim, Physique);
					etudiantService.addFiliereToEtudiant(Hamza, SVT);
					etudiantService.addFiliereToEtudiant(yassin, Physique);
	}
}


