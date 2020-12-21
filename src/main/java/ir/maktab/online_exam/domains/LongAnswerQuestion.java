package ir.maktab.online_exam.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "long_answer_question")
public class LongAnswerQuestion extends Question{
    @Column(name = "answer_text")
    @NotBlank
    @NotEmpty
    private String answerText;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
