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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professeur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professeur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom")
	private String Nom;
	
	@Column(name = "prenom")
	private String Prenom;
	
	@Column(name = "email")
	private String Email ;
	 
	@Column(name="numero_telephone")
	private String NumeroTelephone;
	
	@OneToOne
	@JoinColumn(name = "user_association")
	private Utilisateur user;
	
	@ManyToMany()
	@JoinTable(
			name="profisseur_ensigner_matiere",
			joinColumns= {@JoinColumn(name="id_professeur")},
			inverseJoinColumns= {@JoinColumn(name="id_matiere")}
			)
	private Set<Matiere> matieres; 
	
}
