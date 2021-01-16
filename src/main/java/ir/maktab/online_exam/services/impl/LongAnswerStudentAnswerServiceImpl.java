package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.domains.LongAnswerStudentAnswer;
import ir.maktab.online_exam.repositories.StudentAnswerRepository;
import ir.maktab.online_exam.services.LongAnswerStudentAnswerService;

public class LongAnswerStudentAnswerServiceImpl extends StudentAnswerServiceImpl<LongAnswerStudentAnswer>
        implements LongAnswerStudentAnswerService {

    public LongAnswerStudentAnswerServiceImpl(StudentAnswerRepository<LongAnswerStudentAnswer> repository) {
        super(repository);
    }
}
