package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.repositories.TeacherRepository;
import ir.maktab.online_exam.services.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, TeacherRepository> implements TeacherService {

    private final TeacherRepository repository;

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Teacher findByUsername(String username) {
        return this.repository.findByUsername(username);
    }
}
