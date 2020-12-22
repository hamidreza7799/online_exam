package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.domains.MultipleChoiceQuestion;
import ir.maktab.online_exam.domains.Question;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;
import java.util.Set;

public interface MultipleChoiceQuestionRepository extends QuestionRepository<MultipleChoiceQuestion> {

    @Override
    @EntityGraph(value = "getQuestionChoices")
    Optional<MultipleChoiceQuestion> findById(Long aLong);

    @Override
    @EntityGraph(value = "getQuestionChoices")
    Set<MultipleChoiceQuestion> findByOwnerTeacherId(Long ownerId);

}
