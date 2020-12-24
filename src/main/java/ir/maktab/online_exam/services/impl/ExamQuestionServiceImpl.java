package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.*;
import ir.maktab.online_exam.domains.DTO.CreateExamQuestionDTO;
import ir.maktab.online_exam.domains.DTO.ExamQuestionDTO;
import ir.maktab.online_exam.repositories.ExamQuestionRepository;
import ir.maktab.online_exam.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Set;

@Service
public class ExamQuestionServiceImpl extends BaseServiceImpl<ExamQuestion, ExamQuestionRepository> implements ExamQuestionService {
    private final ExamQuestionRepository repository;
    private final ExamService examService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final LongAnswerQuestionService longAnswerQuestionService;

    public ExamQuestionServiceImpl(ExamQuestionRepository repository, ExamQuestionRepository repository1
            , ExamService examService, MultipleChoiceQuestionService multipleChoiceQuestionService
            , LongAnswerQuestionService longAnswerQuestionService) {
        super(repository);
        this.repository = repository1;
        this.examService = examService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
        this.longAnswerQuestionService = longAnswerQuestionService;
    }

    @Override
    public Set<ExamQuestionDTO> findByExamId(Long examId) {
        //TODO FIX HERE
        return null;
    }

    @Override
    public ResponseEntity<String> deleteExamQuestionById(Long examQuestionId, HttpSession session) {
        Optional<ExamQuestion> examQuestion = this.repository.findById(examQuestionId);
        if(examQuestion.isEmpty())
            //TODO RETURN EXCEPTION
            return ResponseEntity.badRequest().body("This question not exists");
        if(!examQuestion.get().getQuestion().getOwnerTeacher().getId().equals((Long) session.getAttribute("userId")))
            //TODO RETURN EXCEPTION
            return ResponseEntity.badRequest().body("You are not permission for this question.");
        this.repository.delete(examQuestion.get());
        //TODO CHECK QUESTION IS DELETED
        return ResponseEntity.ok("Question delete.");
    }

    @Override
    public ResponseEntity<ExamQuestionDTO> save(Long examId, ExamQuestionDTO examQuestionDTO, HttpSession session) {
        ExamQuestion examQuestion = this.convertToEntity(examQuestionDTO);
        if(examQuestion == null)
            //TODO FIX CONVERT TO RETURN EXCEPTION
            return ResponseEntity.badRequest().build();
        if (!examQuestion.getQuestion().getOwnerTeacher().getId().equals((Long) session.getAttribute("userId")))
            return ResponseEntity.badRequest().build();
        if (examQuestion.getGrade() == null)
            return ResponseEntity.badRequest().build();
        Optional<Exam> exam = examService.findById(examId);
        if (exam.isEmpty())
            return ResponseEntity.badRequest().build();
        examQuestion.setExam(exam.get());
        exam.get().getExamQuestions().add(examQuestion);
        this.save(examQuestion);
        examQuestionDTO.setId(examQuestion.getId());
        return ResponseEntity.ok(examQuestionDTO);
    }

    @Override
    public ExamQuestion convertToEntity(ExamQuestionDTO examQuestionDTO) {
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setId(examQuestionDTO.getId());
        examQuestion.setGrade(examQuestionDTO.getGrade());
        examQuestion.setDescription(examQuestionDTO.getDescription());
        if (examQuestionDTO instanceof CreateExamQuestionDTO) {
            if (((CreateExamQuestionDTO) examQuestionDTO).getQuestionId() == null)
                //TODO RETURN EXCEPTION
                return null;
            if(examQuestionDTO.getQuestionType().equals("MultipleChoice")) {
                Optional<MultipleChoiceQuestion> multipleChoiceQuestion =
                        multipleChoiceQuestionService.findById(((CreateExamQuestionDTO) examQuestionDTO).getQuestionId());
                if(multipleChoiceQuestion.isEmpty())
                    //TODO RETURN EXCEPTION
                    return null;
                examQuestion.setQuestion(multipleChoiceQuestion.get());
            }
            else if(examQuestionDTO.getQuestionType().equals("LongAnswer")) {
                Optional<LongAnswerQuestion> longAnswerQuestion =
                        longAnswerQuestionService.findById(((CreateExamQuestionDTO) examQuestionDTO).getQuestionId());
                if(longAnswerQuestion.isEmpty())
                    //TODO RETURN EXCEPTION
                    return null;
                examQuestion.setQuestion(longAnswerQuestion.get());
            }else
                //TODO RETURN EXCEPTION
                return null;
        }
        return examQuestion;
    }

    @Override
    public ExamQuestionDTO convertToDTO(ExamQuestion examQuestion) {
        return null;
    }
}
