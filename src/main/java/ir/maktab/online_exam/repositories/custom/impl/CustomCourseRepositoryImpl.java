package ir.maktab.online_exam.repositories.custom.impl;

import ir.maktab.online_exam.repositories.custom.CustomCourseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class CustomCourseRepositoryImpl implements CustomCourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean courseHaveStudent(Long courseId, Long userId) {
        Query query = entityManager.createNativeQuery("select fk_course from course_student where fk_course = ? and fk_student = ?");
        query.setParameter(1, courseId);
        query.setParameter(2, userId);
        return query.getResultList().size() != 0;
    }
}
