package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.repositories.MultipleChoiceQuestionRepository;
import ir.maktab.online_exam.repositories.QuestionRepository;
import ir.maktab.online_exam.services.MultipleChoiceQuestionService;

public class MultipleChoiceQuestionServiceImpl extends QuestionServiceImpl implements MultipleChoiceQuestionService {
    private MultipleChoiceQuestionRepository repository;

    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
