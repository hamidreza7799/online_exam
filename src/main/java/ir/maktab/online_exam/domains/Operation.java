package ir.maktab.online_exam.domains;

import ir.maktab.online_exam.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_operation")
public class Operation extends BaseEntity {

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
