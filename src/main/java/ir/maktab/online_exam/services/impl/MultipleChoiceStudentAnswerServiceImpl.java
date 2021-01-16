package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.domains.DTO.MultipleChoiceStudentAnswerDTO;
import ir.maktab.online_exam.domains.MultipleChoiceStudentAnswer;
import ir.maktab.online_exam.repositories.StudentAnswerRepository;
import ir.maktab.online_exam.services.MultipleChoiceStudentAnswerService;

public class MultipleChoiceStudentAnswerServiceImpl extends StudentAnswerServiceImpl<MultipleChoiceStudentAnswer>
        implements MultipleChoiceStudentAnswerService {

    public MultipleChoiceStudentAnswerServiceImpl(StudentAnswerRepository<MultipleChoiceStudentAnswer> repository) {
        super(repository);
    }

    @Override
    public MultipleChoiceStudentAnswer convertToEntity(MultipleChoiceStudentAnswerDTO studentAnswerDTO) {
        return null;
    }

    @Override
    public MultipleChoiceStudentAnswerDTO convertToDTO(MultipleChoiceStudentAnswer studentAnswer) {
        return null;
    }
}
