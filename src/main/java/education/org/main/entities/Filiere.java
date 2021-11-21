package education.org.main.entities;

import java.time.LocalDate;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="filiere")
public class Filiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "titre") 
	private String titre;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(mappedBy = "filieres")
	private List<Etudiant> etudiants;
	
	@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinTable(
		        name = "associer_filiere_matiere", 
		        joinColumns = {@JoinColumn(name="id_filiere")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_matiere")}
		    )
	private List<Matiere> matieres;
	
	@ManyToMany(mappedBy = "filieres", fetch = FetchType.LAZY)
	private List<NiveauScolaire> niveauScolaires;

	public Filiere(String titre, String description, List<Etudiant> etudiants, List<Matiere> matieres,
			List<NiveauScolaire> niveauScolaires) {
		super();
		this.titre = titre;
		this.description = description;
		this.etudiants = etudiants;
		this.matieres = matieres;
		this.niveauScolaires = niveauScolaires;
	}
	
	public Filiere() {
	}
	
	public void addEtudiantToFiliere(Etudiant etudiant)
	{
		this.etudiants.add(etudiant);
		etudiant.getFilieres().add(this);
	}
	
	public void addNiveauScolaireToFiliere(NiveauScolaire niveauScolaire)
	{
		this.niveauScolaires.add(niveauScolaire);
		niveauScolaire.getFilieres().add(this);
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

	@JsonIgnore
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	public List<NiveauScolaire> getNiveauScolaires() {
		return niveauScolaires;
	}

	public void setNiveauScolaires(List<NiveauScolaire> niveauScolaires) {
		this.niveauScolaires = niveauScolaires;
	}

	}
