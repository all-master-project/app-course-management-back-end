package education.org.main.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="filiere")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "titre")
	private String Titre;
	
	@Column(name = "description")
	private String Description;
	
	@ManyToMany(mappedBy = "filieres")
	private Set<Etudiant> etudiants;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		        name = "associer_filiere_matiere", 
		        joinColumns = {@JoinColumn(name="id_filiere")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_matiere")}
		    )
	private Set<Matiere> matieres;
	
	@ManyToMany(mappedBy = "filieres")
	private Set<NiveauScolaire> niveauScolaires;
		
	//Niveau scolaire cascade
	
	}
