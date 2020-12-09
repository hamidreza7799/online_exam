package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Role;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.repositories.RoleRepository;
import ir.maktab.online_exam.repositories.TeacherRepository;
import ir.maktab.online_exam.services.RoleService;
import ir.maktab.online_exam.services.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends UserServiceImpl<Teacher> implements TeacherService {

    private final TeacherRepository repository;
    private final RoleService roleService;

    public TeacherServiceImpl(TeacherRepository repository, RoleService roleService) {
        super(repository);
        this.repository = repository;
        this.roleService = roleService;
    }

    @Override
    public Teacher save(Teacher teacher){
        teacher.getRoles().add(
               roleService.findByTitle("TEACHER")
        );
        return this.repository.save(teacher);
    }

}
