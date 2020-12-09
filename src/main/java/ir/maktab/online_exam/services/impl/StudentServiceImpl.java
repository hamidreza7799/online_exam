package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Role;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.repositories.StudentRepository;
import ir.maktab.online_exam.services.RoleService;
import ir.maktab.online_exam.services.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends UserServiceImpl<Student> implements StudentService {

    private final StudentRepository repository;
    private final RoleService roleService;

    public StudentServiceImpl(StudentRepository repository, RoleService roleService) {
        super(repository);
        this.repository = repository;
        this.roleService = roleService;
    }

    @Override
    public Student save(Student student) {
        student.getRoles().add(
                roleService.findByTitle("STUDENT")
        );
        return super.save(student);
    }
}
