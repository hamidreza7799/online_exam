package ir.maktab.online_exam.domains;

import ir.maktab.online_exam.base.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_role")
public class Role extends BaseEntity {

    @Column(name = "title", unique = true)
    @NotNull
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_role_operations",
            joinColumns = {@JoinColumn(name = "fk_role")},
            inverseJoinColumns = {@JoinColumn(name = "fk_operation")}
    )
    private Set<Operation> operations = new HashSet<>();


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }
}
