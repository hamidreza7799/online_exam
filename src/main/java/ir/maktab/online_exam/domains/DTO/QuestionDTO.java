package ir.maktab.online_exam.domains.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class QuestionDTO {
    private Long id;
    private String title;
    private String questionText;
    private String answerText;
    private List<QuestionChoiceDTO> questionChoice;

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

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public List<QuestionChoiceDTO> getQuestionChoice() {
        return questionChoice;
    }

    public void setQuestionChoice(List<QuestionChoiceDTO> questionChoice) {
        this.questionChoice = questionChoice;
    }
}
