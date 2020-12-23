package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.domains.MultipleChoiceQuestion;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.repositories.MultipleChoiceQuestionRepository;
import ir.maktab.online_exam.services.MultipleChoiceQuestionService;
import ir.maktab.online_exam.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class MultipleChoiceQuestionServiceImpl extends QuestionServiceImpl<MultipleChoiceQuestion> implements MultipleChoiceQuestionService {

    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository repository, TeacherService teacherService) {
        super(repository, teacherService);
    }

    @Override
    public ResponseEntity<String> save(MultipleChoiceQuestion question, HttpSession session) {
        if(question.getQuestionChoices() == null || question.getQuestionChoices().size() == 0)
            return ResponseEntity.badRequest().body("Multiple choice question should have at least one choice.");
        return super.save(question, session);
    }
}
