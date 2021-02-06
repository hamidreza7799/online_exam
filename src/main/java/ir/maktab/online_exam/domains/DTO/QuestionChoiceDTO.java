package ir.maktab.online_exam.domains.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class QuestionChoiceDTO {

    private String choiceText;
    private Boolean isAnswer = false;

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public Boolean getAnswer() {
        return isAnswer;
    }

    public void setAnswer(Boolean answer) {
        isAnswer = answer;
    }
}
