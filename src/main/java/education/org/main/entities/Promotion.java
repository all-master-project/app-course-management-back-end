package education.org.main.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "promotion")

public class Promotion {
	
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(name = "titre")
	private String titre; 
	
	@Column(name = "description")
	private String description;
 
	@Column(name = "date_debut")
	private LocalDate dateDebut;
	
	@Column(name = "date_fin")
	private LocalDate dateFin;
	
	@Column(name = "prix")
	private Double prix;
	
	@ManyToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
	private List<Etudiant> etudiants= new ArrayList<>();

	public Promotion(String titre, String description, LocalDate dateDebut, LocalDate dateFin, Double prix,
			List<Etudiant> etudiants) {
		super();
		this.titre = titre;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prix = prix;
		this.etudiants = etudiants;
	}  
	
	public Promotion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	@JsonIgnore
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	 
}
