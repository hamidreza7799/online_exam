package ir.maktab.online_exam.domains.DTO;

public class LongAnswerQuestionDTO extends QuestionDTO{

    private String answerText;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
