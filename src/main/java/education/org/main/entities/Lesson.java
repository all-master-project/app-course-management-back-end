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
@Table(name = "lesson" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titre")
	private String Titre;
	
	@Column(name = "courPDF")
	private byte [] CourPDF;
	
	@Column(name = "exercicePDF")
	private byte [] exercicePDF;
	
	@Column(name = "video_explication")
	private byte [] videoExplication;

	
	@ManyToMany
	@JoinTable(
			name="lesson_matiere",
			joinColumns= {@JoinColumn(name="id_lesson")},
			inverseJoinColumns= {@JoinColumn(name="id_matiere")}
	)
	private Set<Matiere> matieres;
	
	public void addMatiere(Matiere matiere)
	{
		this.matieres.add(matiere);
		matiere.getLessons().add(this);
	}
	
	public void removeMatiere(Matiere matiere)
	{
		this.matieres.remove(matiere);
		matiere.getLessons().remove(this);
	}
	
}
