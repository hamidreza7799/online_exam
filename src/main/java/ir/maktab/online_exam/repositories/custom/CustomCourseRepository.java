package ir.maktab.online_exam.repositories.custom;

public interface CustomCourseRepository {

    Boolean courseHaveStudent(Long courseId, Long userId);
}
