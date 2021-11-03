package education.org.main.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role" )
@AllArgsConstructor
@NoArgsConstructor

public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role_name")
	private String RoleName;
	
	@Column(name = "description_role")
	private String Description;
	
	@OneToMany(mappedBy = "role")
	private Set<Etudiant> etudiant;

}
 