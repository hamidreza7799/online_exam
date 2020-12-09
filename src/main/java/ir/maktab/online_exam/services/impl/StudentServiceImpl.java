package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.repositories.StudentRepository;
import ir.maktab.online_exam.services.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends UserServiceImpl<Student> implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
