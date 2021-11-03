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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "promotion")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Promotion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@ManyToMany(mappedBy = "promotions")
	private Set<Etudiant> etudiants;  
	
	 
}
