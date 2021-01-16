package ir.maktab.online_exam.domains.DTO;

import ir.maktab.online_exam.domains.QuestionChoice;

import java.util.List;

public class MultipleChoiceStudentAnswerDTO extends StudentAnswerDTO{

    private List<QuestionChoiceDTO> studentChoices;

    public List<QuestionChoiceDTO> getStudentChoices() {
        return studentChoices;
    }

    public void setStudentChoices(List<QuestionChoiceDTO> studentChoices) {
        this.studentChoices = studentChoices;
    }
}
