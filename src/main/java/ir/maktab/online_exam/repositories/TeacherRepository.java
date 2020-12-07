package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Teacher;

public interface TeacherRepository extends BaseRepository<Teacher> {

    Teacher findByUsername(String username);
}
