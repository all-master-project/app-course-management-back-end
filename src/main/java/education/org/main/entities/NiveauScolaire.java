package education.org.main.entities;

import java.util.Set;

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
@Table(name="niveau_scolaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NiveauScolaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "titre")
	private String nom_niveau_scolaire;
	
	@Column(name = "description")
	private String Description;
	
	@ManyToMany()
	@JoinTable(
			name="filiere_niveau_scolaire",
			joinColumns= {@JoinColumn(name="id_niveau_scolaire")},
			inverseJoinColumns= {@JoinColumn(name="id_filiere")}
			)
	private Set<Filiere> filieres;
	 
	
}
