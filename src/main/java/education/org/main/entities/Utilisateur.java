package education.org.main.entities;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "Utilisateur")
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id; 
    
    @Column(name = "`user_name`")
    private String username;
    
    @Column( name = "`password`")
    private String password;
    
    private int active;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    		name="user_role",
    		joinColumns =  @JoinColumn(name="id_utilisateur"),
    		inverseJoinColumns =  @JoinColumn(name="id_role")
    		)
    private List<Role> roles  ;
    
    @Column(name = "`email`")
    private String email;
    
    @Column(name = "`phone`")
    private String phone;
    
    public Utilisateur() {
	}
    
	public Utilisateur(String username, String password, int active, List<Role> roles, String email, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.email = email;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	} 

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Transactional
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}