package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.domains.Exam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExamRepository extends BaseRepository<Exam> {
    List<Exam> findByCourseId(Long id);
}
