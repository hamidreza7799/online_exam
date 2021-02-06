package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.repositories.custom.CustomTeacherRepository;

public interface TeacherRepository extends UserRepository<Teacher>, CustomTeacherRepository {
}
