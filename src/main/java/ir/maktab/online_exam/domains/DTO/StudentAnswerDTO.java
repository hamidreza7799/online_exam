package ir.maktab.online_exam.domains.DTO;

import java.io.Serializable;

public abstract class StudentAnswerDTO implements Serializable {
    private Long studentId;
    private Long examQuestionId;
    private Long grade;
    private String teacherComment;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExamQuestionId() {
        return examQuestionId;
    }

    public void setExamQuestionId(Long examQuestionId) {
        this.examQuestionId = examQuestionId;
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
