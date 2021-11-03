package education.org.main.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="etudiant")
@Data
@NoArgsConstructor
public class Etudiant {
	
	
	
	public Etudiant(Long id, String nom, String prenom, String numeroTelephone, String email, User user, Role role,
			Set<Promotion> promotions, Set<Filiere> filieres) {
		this.id = id;
		Nom = nom;
		Prenom = prenom;
		this.numeroTelephone = numeroTelephone;
		this.email = email;
		this.user = user;
		this.role = role;
		this.promotions = promotions;
		this.filieres = filieres;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "nom")
	private String Nom;
	
	@Column(name = "prenom")
	private String Prenom;
	
	@Column(name = "numero_telephone")
	private String numeroTelephone;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne
	private User user; 
	
	@ManyToOne()
	@JoinColumn(name = "id_role")
	private Role role;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		        name = "inscrire", 
		        joinColumns = {@JoinColumn(name="id_etudiant")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_promotion")}
		    )
	private Set<Promotion> promotions;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		        name = "inscrire", 
		        joinColumns = {@JoinColumn(name="id_etudiant")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_filiere")}
		    )
	private Set<Filiere> filieres;
	
	public void addFiliere(Filiere filiere)
	{
		this.filieres.add(filiere);
		filiere.getEtudiants().add(this);
	}
	
	public void removeFiliere(Filiere filiere)
	{
		this.filieres.remove(filiere);
		filiere.getEtudiants().remove(this);
	}
	
}
