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
import javax.persistence.OneToOne;
import javax.persistence.Table;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@Table(name="etudiant")
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_association")
	private Utilisateur user;
	 
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable( 
		        name = "abonner", 
		        joinColumns = {@JoinColumn(name="id_etudiant")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_promotion")}
		    )
	private List<Promotion> promotions = new ArrayList<Promotion>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(   
		        name = "inscrire", 
		        joinColumns = {@JoinColumn(name="id_etudiant")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_filiere")}
		    )
	private List<Filiere> filieres= new ArrayList<Filiere>();

	public Etudiant(String nom, String prenom, Utilisateur user, List<Promotion> promotions, List<Filiere> filieres) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.user = user;
		this.promotions = promotions;
		this.filieres = filieres;
	}

	public Long getEtudiant_id() {
		return etudiant_id;
	}

	public void setEtudiant_id(Long etudiant_id) {
		this.etudiant_id = etudiant_id;
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

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
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
