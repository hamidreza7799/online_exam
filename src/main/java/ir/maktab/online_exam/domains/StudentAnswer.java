package ir.maktab.online_exam.domains;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@MappedSuperclass
public abstract class StudentAnswer implements Serializable {

    @EmbeddedId
    private StudentAnswerID id = new StudentAnswerID();

    @ManyToOne
    @MapsId("studentID")
    private Student student;

    @ManyToOne
    @MapsId("examQuestionID")
    private ExamQuestion examQuestion;

    @Column(name = "grade")
    private Long grade;

    @Column(name = "teacher_comment")
    @Size(max = 125)
    private String teacherComment;

    public StudentAnswerID getId() {
        return id;
    }

    public void setId(StudentAnswerID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ExamQuestion getExamQuestion() {
        return examQuestion;
    }

    public void setExamQuestion(ExamQuestion examQuestion) {
        this.examQuestion = examQuestion;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }
}
