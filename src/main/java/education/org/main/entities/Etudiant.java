package education.org.main.entities;

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
import javax.persistence.Table;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@Table(name="etudiant")
//@Data
@NoArgsConstructor
public class Etudiant implements Serializable{

	private static final long serialVersionUID = -105154683842428010L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long etudiant_id;
	
	@Column(name = "nom")
	private String nom;
	 
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "numero_telephone")
	private String numeroTelephone;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password") 
	private String password;
	 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
	        name = "etudiant_role", 
	        joinColumns = {@JoinColumn(name="etudiant_id")}, 
	        inverseJoinColumns = {@JoinColumn(name="role_id")}
	    )
	private List<Role> roles;
	 
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable( 
		        name = "abonner", 
		        joinColumns = {@JoinColumn(name="id_etudiant")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_promotion")}
		    )
	private List<Promotion> promotions= new ArrayList<Promotion>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(   
		        name = "inscrire", 
		        joinColumns = {@JoinColumn(name="id_etudiant")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_filiere")}
		    )
	private List<Filiere> filieres= new ArrayList<Filiere>();

	public Etudiant(String nom, String prenom, String numeroTelephone, String email, String username, String password,
			List<Role> roles, List<Promotion> promotions, List<Filiere> filieres) {
		super();
		this.nom = nom; 
		this.prenom = prenom;
		this.numeroTelephone = numeroTelephone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles; 
		this.promotions = promotions;
		this.filieres = filieres;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	} 

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}
	
	
}
