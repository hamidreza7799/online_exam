package ir.maktab.online_exam.domains;

import ir.maktab.online_exam.base.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
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
}
