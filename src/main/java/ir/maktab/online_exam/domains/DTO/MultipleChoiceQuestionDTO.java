package ir.maktab.online_exam.domains.DTO;

import java.util.List;

public class MultipleChoiceQuestionDTO extends QuestionDTO{

    private List<QuestionChoiceDTO> questionChoice;

    public List<QuestionChoiceDTO> getQuestionChoice() {
        return questionChoice;
    }

    public void setQuestionChoice(List<QuestionChoiceDTO> questionChoice) {
        this.questionChoice = questionChoice;
    }
}
