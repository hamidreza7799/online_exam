package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.repositories.StudentRepository;
import ir.maktab.online_exam.services.StudentService;

public class StudentServiceImpl extends BaseServiceImpl<Student, StudentRepository> implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }
}
