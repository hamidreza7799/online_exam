package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.DTO.QuestionDTO;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.repositories.QuestionRepository;
import ir.maktab.online_exam.services.QuestionService;
import ir.maktab.online_exam.services.TeacherService;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class QuestionServiceImpl<E extends Question, DTO extends QuestionDTO> extends BaseServiceImpl<E, QuestionRepository<E>> implements QuestionService<E, DTO> {
    protected final QuestionRepository<E> repository;
    protected final TeacherService teacherService;

    public QuestionServiceImpl(QuestionRepository<E> repository, TeacherService teacherService) {
        super(repository);
        this.repository = repository;
        this.teacherService = teacherService;
    }

    @Override
    public Set<DTO> findByOwnerTeacherId(HttpSession session) {
        if(session.getAttribute("userId") != null)
            return this.repository
                    .findByOwnerTeacherId((Long) session.getAttribute("userId"))
                    .stream().map(this::convertToDTO).collect(Collectors.toSet());
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

    protected ResponseEntity<String> save(E question, HttpSession session) {
        if(question.getQuestionText() == null || question.getQuestionText().isEmpty() || question.getQuestionText().isBlank())
            return ResponseEntity.badRequest().body("Question text should not empty or blank");
        //new question
        if(question.getOwnerTeacher() == null){
            Optional<Teacher> teacher = this.teacherService.findById((Long) session.getAttribute("userId"));
            if(teacher.isEmpty())
                return ResponseEntity.badRequest().body("You are not a teacher.");
            else
                question.setOwnerTeacher(teacher.get());
        }
        //old question
        else {
            if(!question.getOwnerTeacher().getId().equals((Long) session.getAttribute("userId")))
                return ResponseEntity.badRequest().body("You are not permission for this request.");
        }
        this.repository.save(question);
        return ResponseEntity.ok("Question is create");
    }
}
