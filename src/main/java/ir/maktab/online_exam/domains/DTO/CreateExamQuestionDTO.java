package ir.maktab.online_exam.domains.DTO;


public class CreateExamQuestionDTO extends ExamQuestionDTO {
    private Long questionId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
