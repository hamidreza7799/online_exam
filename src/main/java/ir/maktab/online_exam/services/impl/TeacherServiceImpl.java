package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.repositories.TeacherRepository;
import ir.maktab.online_exam.services.TeacherService;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher, TeacherRepository> implements TeacherService {

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }
}
