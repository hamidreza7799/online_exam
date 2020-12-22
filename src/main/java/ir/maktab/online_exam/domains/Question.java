package ir.maktab.online_exam.domains;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tbl_question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "title")
    @NotBlank @NotEmpty
    private String title;
    @Column(name = "question_text")
    @NotBlank @NotEmpty
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "fk_owner_teacher")
    @NotNull
    private Teacher ownerTeacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Teacher getOwnerTeacher() {
        return ownerTeacher;
    }

    public void setOwnerTeacher(Teacher ownerTeacher) {
        this.ownerTeacher = ownerTeacher;
    }
}
