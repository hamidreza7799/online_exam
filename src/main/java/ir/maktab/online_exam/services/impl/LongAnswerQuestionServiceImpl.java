package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.domains.LongAnswerQuestion;
import ir.maktab.online_exam.repositories.LongAnswerQuestionRepository;
import ir.maktab.online_exam.services.LongAnswerQuestionService;
import ir.maktab.online_exam.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LongAnswerQuestionServiceImpl extends QuestionServiceImpl<LongAnswerQuestion> implements LongAnswerQuestionService {
    public LongAnswerQuestionServiceImpl(LongAnswerQuestionRepository repository, TeacherService teacherService) {
        super(repository, teacherService);
    }

    @Override
    public ResponseEntity<String> save(LongAnswerQuestion question, HttpSession session) {
        if(question.getAnswerText() == null || question.getAnswerText().isEmpty() || question.getAnswerText().isBlank())
            return ResponseEntity.badRequest().body("Text of answer should not empty or blank.");
        return super.save(question, session);
    }
}
