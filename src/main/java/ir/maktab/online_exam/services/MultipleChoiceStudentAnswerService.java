package ir.maktab.online_exam.services;

import ir.maktab.online_exam.domains.DTO.MultipleChoiceStudentAnswerDTO;
import ir.maktab.online_exam.domains.MultipleChoiceStudentAnswer;

public interface MultipleChoiceStudentAnswerService extends StudentAnswerService<MultipleChoiceStudentAnswer> {

    MultipleChoiceStudentAnswer convertToEntity(MultipleChoiceStudentAnswerDTO studentAnswerDTO);

    MultipleChoiceStudentAnswerDTO convertToDTO(MultipleChoiceStudentAnswer studentAnswer);
}
