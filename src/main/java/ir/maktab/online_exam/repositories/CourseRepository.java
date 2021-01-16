package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.repositories.custom.CustomCourseRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends BaseRepository<Course>, CustomCourseRepository {
    @Override
    @EntityGraph(value = "getCourseTeacher", type = EntityGraph.EntityGraphType.FETCH)
    List<Course> findAll();
}
