package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Exam;
import ir.maktab.online_exam.repositories.ExamRepository;
import ir.maktab.online_exam.services.ExamService;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl extends BaseServiceImpl<Exam, ExamRepository> implements ExamService {
    private final ExamRepository repository;
    public ExamServiceImpl(ExamRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
