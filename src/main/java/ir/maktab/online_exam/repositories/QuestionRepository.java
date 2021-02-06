package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.domains.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Entity;
import java.util.Optional;
import java.util.Set;

@NoRepositoryBean
public interface QuestionRepository<E extends Question> extends BaseRepository<E> {

    @Override
    @EntityGraph(value = "getOwnerTeacher")
    Optional<E> findById(Long aLong);

    Set<E> findByOwnerTeacherId(Long ownerId);
}
