package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.domains.Teacher;

import java.util.Set;

public interface QuestionRepository extends BaseRepository<Question> {

    Set<Question> findByOwnerTeacherId(Long ownerId);

}
