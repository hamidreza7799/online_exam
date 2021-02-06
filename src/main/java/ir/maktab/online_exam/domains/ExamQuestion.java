package ir.maktab.online_exam.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "exam_question")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "getQuestionOfExamQuestion",
                attributeNodes = {
                        @NamedAttributeNode(value = "question", subgraph = "getTeacherOfQuestion")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "getTeacherOfQuestion",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "ownerTeacher")
                                }
                        )
                }
        )
})
public class ExamQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "grade")
    @NotNull
    private Double grade;
    @Column(name = "description")
    @Size(max = 4096)
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_question")
    @NotNull
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_exam")
    @NotNull
    private Exam exam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
