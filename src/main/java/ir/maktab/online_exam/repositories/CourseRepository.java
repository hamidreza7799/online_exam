package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Course;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface CourseRepository extends BaseRepository<Course> {
    @Override
    @EntityGraph(value = "getCourseTeacher", type = EntityGraph.EntityGraphType.FETCH)
    List<Course> findAll();
}
