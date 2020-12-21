package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.repositories.LongAnswerQuestionRepository;
import ir.maktab.online_exam.repositories.QuestionRepository;
import ir.maktab.online_exam.services.LongAnswerQuestionService;

public class LongAnswerQuestionServiceImpl extends QuestionServiceImpl implements LongAnswerQuestionService {
    private final LongAnswerQuestionRepository repository;

    public LongAnswerQuestionServiceImpl(LongAnswerQuestionRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
