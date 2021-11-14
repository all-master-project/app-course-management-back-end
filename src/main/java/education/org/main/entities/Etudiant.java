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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="etudiant")
@Data
@NoArgsConstructor
public class Etudiant implements Serializable{

	private static final long serialVersionUID = -105154683842428010L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "numero_telephone", unique = true)
	private String numeroTelephone;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Role> roles;
	 
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
		        name = "inscrire", 
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
}
