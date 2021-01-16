package ir.maktab.online_exam.domains;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_multiple_choice_student_answer")
public class MultipleChoiceStudentAnswer extends StudentAnswer{

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "multiple_choice_student_choices",
            joinColumns = {@JoinColumn(name = "student_id"), @JoinColumn(name = "exam_question_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_choice")}
    )
    private Set<QuestionChoice> studentChoices = new HashSet<>();

    public Set<QuestionChoice> getStudentChoices() {
        return studentChoices;
    }

    public void setStudentChoices(Set<QuestionChoice> studentChoices) {
        this.studentChoices = studentChoices;
    }
}
