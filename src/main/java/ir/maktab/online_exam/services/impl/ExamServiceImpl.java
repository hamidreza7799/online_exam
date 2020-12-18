package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Exam;
import ir.maktab.online_exam.repositories.ExamRepository;
import ir.maktab.online_exam.services.ExamService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamServiceImpl extends BaseServiceImpl<Exam, ExamRepository> implements ExamService {
    private final ExamRepository repository;
    public ExamServiceImpl(ExamRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Exam save(Exam exam) {
        if(exam.getStartDateTime().isAfter(exam.getEndDateTime()) || exam.getEndDateTime().isEqual(exam.getStartDateTime()))
            //TODO RETURN EXCEPTION
            return null;
        if(Duration.between(LocalDateTime.now(), exam.getStartDateTime()).toMinutes() < 1)
            //TODO RETURN EXCEPTION, FIX DURATION BETWEEN START-TIME AND NOW
            return null;
        if(Duration.between(exam.getStartDateTime(), exam.getEndDateTime()).toMinutes() < exam.getTime())
            //TODO RETURN EXCEPTION
            return null;
        return repository.save(exam);
    }

    @Override
    public List<Exam> findByCourseId(Long id) {
        return repository.findByCourseId(id);
    }
}
