package education.org.main.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "Utilisateur")
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "`user_name`", nullable = false)
    private String username;
    @Column( name = "`password`",nullable = false)
    private String password;
    private int active;
    private String roles = "";
    private String permissions = "";
    @Column(name = "`email`")
    private String email;
    @Column(name = "`full_name`")
    private String fullName;



    public Utilisateur(String username, String password, String roles, String permissions, String email,
                       String fullName) {
        super();
        this.username = username;
        this.password = password;
        this.active = 1;
        this.roles = roles;
        this.permissions = permissions;
        this.email = email;
        this.fullName = fullName;
    }

    public Utilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getPermissions() {
        return permissions;
    }
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<String> getRoleList(){
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}