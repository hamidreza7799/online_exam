package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.repositories.QuestionRepository;
import ir.maktab.online_exam.services.QuestionService;

import java.util.Set;

public class QuestionServiceImpl extends BaseServiceImpl<Question, QuestionRepository> implements QuestionService {
    private final QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Set<Question> findByOwnerTeacherId(Long ownerId) {
        return this.repository.findByOwnerTeacherId(ownerId);
    }
}
