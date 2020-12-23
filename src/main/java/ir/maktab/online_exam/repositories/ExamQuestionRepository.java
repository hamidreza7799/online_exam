package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.ExamQuestion;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;
import java.util.Set;

public interface ExamQuestionRepository extends BaseRepository<ExamQuestion> {

    Set<ExamQuestion> findByExamId(Long examId);

    @Override
    @EntityGraph(value = "getQuestionOfExamQuestion")
    Optional<ExamQuestion> findById(Long aLong);
}
