package ir.maktab.online_exam.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "long_answer_student_answer")
public class LongAnswerStudentAnswer extends StudentAnswer{

    @Column(name = "student_answer")
    private String studentAnswer;

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
}
