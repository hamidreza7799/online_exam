package ir.maktab.online_exam.domains;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudentAnswerID implements Serializable {

    private Long studentID;
    private Long examQuestionID;

    public StudentAnswerID(){}

    public StudentAnswerID(Long studentID, Long examQuestionID){
        super();
        this.studentID = studentID;
        this.examQuestionID = examQuestionID;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getExamQuestionID() {
        return examQuestionID;
    }

    public void setExamQuestionID(Long examQuestionID) {
        this.examQuestionID = examQuestionID;
    }
}
