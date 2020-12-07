package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.repositories.TeacherRepository;

public interface TeacherService extends BaseService<Teacher, TeacherRepository> {

    Teacher findByUsername(String username);
}
