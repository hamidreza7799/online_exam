package ir.maktab.online_exam.domains;

import ir.maktab.online_exam.base.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class User extends BaseEntity {
    @Column(name = "username", unique = true)
    @NotBlank @NotEmpty @Size(min = 6, max = 25)
    private String username;
    @Column(name = "password")
    @NotBlank @NotEmpty @Size(min = 6, max = 25)
    private String password;
    @Column(name = "first_name")
    @NotBlank @NotEmpty
    private String firstName;
    @Column(name = "last_name")
    @NotBlank @NotEmpty
    private String lastName;
    @Column(name = "email", unique = true)
    @NotNull @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role",
            joinColumns = {@JoinColumn(name = "fk_user")},
            inverseJoinColumns = {@JoinColumn(name = "fk_role")}
    )
    private Set<Role> roles = new HashSet<>();

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
