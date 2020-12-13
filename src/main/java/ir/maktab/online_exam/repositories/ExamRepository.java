package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.domains.Exam;

import java.util.Set;

public interface ExamRepository extends BaseRepository<Exam> {

    Set<Exam> findAllByCourse(Course course);
}
