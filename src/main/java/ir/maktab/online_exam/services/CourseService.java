package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.repositories.CourseRepository;

import java.util.Optional;

public interface CourseService extends BaseService<Course, CourseRepository> {
    Boolean courseHaveStudent(Long courseId, Long userId);
}
