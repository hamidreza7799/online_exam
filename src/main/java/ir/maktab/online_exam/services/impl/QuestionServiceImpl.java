package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.repositories.QuestionRepository;
import ir.maktab.online_exam.services.QuestionService;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class QuestionServiceImpl<E extends Question> extends BaseServiceImpl<E, QuestionRepository<E>> implements QuestionService<E> {
    private final QuestionRepository<E> repository;

    public QuestionServiceImpl(QuestionRepository<E> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Set<E> findByOwnerTeacherId(HttpSession session) {
        if(session.getAttribute("userId") != null)
            return this.repository.findByOwnerTeacherId((Long) session.getAttribute("userId"));
        else
            return new HashSet<>();
    }

    @Override
    public ResponseEntity<String> deleteById(Long id, HttpSession session) {
        Optional<E> question = this.repository.findById(id);
        if(question.isEmpty())
            return ResponseEntity.notFound().build();
        if(!question.get().getOwnerTeacher().getId().equals(session.getAttribute("userId")))
            return ResponseEntity.badRequest().body("You are not permission for this action.");
        super.deleteById(id);
        if(this.repository.findById(id).isPresent())
            return ResponseEntity.badRequest().body("Server can not delete this question.");
        return ResponseEntity.ok("Question delete.");
    }
}
