package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.domains.MultipleChoiceQuestion;
import ir.maktab.online_exam.repositories.MultipleChoiceQuestionRepository;
import ir.maktab.online_exam.repositories.QuestionRepository;
import ir.maktab.online_exam.services.MultipleChoiceQuestionService;
import org.springframework.stereotype.Service;

@Service
public class MultipleChoiceQuestionServiceImpl extends QuestionServiceImpl<MultipleChoiceQuestion> implements MultipleChoiceQuestionService {
    private MultipleChoiceQuestionRepository repository;

    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
