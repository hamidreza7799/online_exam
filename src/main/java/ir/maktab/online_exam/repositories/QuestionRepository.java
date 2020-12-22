package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.domains.Teacher;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Set;

@NoRepositoryBean
public interface QuestionRepository<E extends Question> extends BaseRepository<E> {

    Set<E> findByOwnerTeacherId(Long ownerId);
}
