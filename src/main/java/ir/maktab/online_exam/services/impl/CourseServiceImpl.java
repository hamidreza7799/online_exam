package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.repositories.CourseRepository;
import ir.maktab.online_exam.services.CourseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, CourseRepository> implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Boolean courseHaveStudent(Long courseId, Long userId) {
        return repository.courseHaveStudent(courseId, userId);
    }
}
