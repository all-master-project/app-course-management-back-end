package education.org.main.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="matiere")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "titre")
	private String Titre;
	
	@Column(name = "description")
	private String Description;
	
	@ManyToMany(mappedBy = "matieres")
	private Set<Filiere> filieres;
	
	@ManyToMany(mappedBy = "matieres")
	private Set<Lesson> lessons; 

	@ManyToMany(mappedBy = "matieres")
	private Set<Professeur> professeurs; 
	
	
}
