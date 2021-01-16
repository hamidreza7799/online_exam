package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.StudentAnswer;
import ir.maktab.online_exam.repositories.StudentAnswerRepository;
import ir.maktab.online_exam.repositories.StudentRepository;
import ir.maktab.online_exam.services.StudentAnswerService;

public class StudentAnswerServiceImpl<E extends StudentAnswer> extends BaseServiceImpl<E, StudentAnswerRepository<E>>
        implements StudentAnswerService<E> {

    protected final StudentAnswerRepository<E> repository;

    public StudentAnswerServiceImpl(StudentAnswerRepository<E> repository) {
        super(repository);
        this.repository = repository;
    }
}
