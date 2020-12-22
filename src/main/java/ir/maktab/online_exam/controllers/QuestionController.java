package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.DTO.QuestionDTO;
import ir.maktab.online_exam.domains.LongAnswerQuestion;
import ir.maktab.online_exam.domains.MultipleChoiceQuestion;
import ir.maktab.online_exam.services.LongAnswerQuestionService;
import ir.maktab.online_exam.services.MultipleChoiceQuestionService;
import ir.maktab.online_exam.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final ModelMapper modelMapper;
    private final TeacherService teacherService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final LongAnswerQuestionService longAnswerQuestionService;

    public QuestionController(ModelMapper modelMapper, TeacherService teacherService
            , MultipleChoiceQuestionService multipleChoiceQuestionService, LongAnswerQuestionService longAnswerQuestionService) {
        this.modelMapper = modelMapper;
        this.teacherService = teacherService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
        this.longAnswerQuestionService = longAnswerQuestionService;
    }

    @GetMapping
    public List<QuestionDTO> getTeacherQuestions(HttpSession session) {
        //TODO RETURN ESSENTIAL EXCEPTION
        List<QuestionDTO> result = new ArrayList<>();
        result.addAll(
                multipleChoiceQuestionService
                        .findByOwnerTeacherId(session)
                        .stream()
                        .map(this::convertToMultipleChoiceQuestionDTO)
                        .collect(Collectors.toList())
        );
        result.addAll(
                longAnswerQuestionService
                        .findByOwnerTeacherId(session)
                        .stream()
                        .map(this::convertToLongAnswerQuestionDTO)
                        .collect(Collectors.toList())
        );
        return result;
    }

    @GetMapping("/multiple-choice")
    public List<QuestionDTO> getTeacherMultipleChoiceQuestions(HttpSession session) {
        //TODO RETURN ESSENTIAL EXCEPTION
        return multipleChoiceQuestionService
                .findByOwnerTeacherId(session)
                .stream()
                .map(this::convertToMultipleChoiceQuestionDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/long-answer")
    public List<QuestionDTO> getTeacherLongAnswerQuestions(HttpSession session) {
        //TODO RETURN ESSENTIAL EXCEPTION
        return longAnswerQuestionService
                        .findByOwnerTeacherId(session)
                        .stream()
                        .map(this::convertToLongAnswerQuestionDTO)
                        .collect(Collectors.toList());

    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId, HttpSession session){
        //TODO WRITE QUESTION SERVICE OR NO?
        ResponseEntity<String> result;
        result = multipleChoiceQuestionService.deleteById(questionId, session);
        if(result.getStatusCode().value() == 404)
            return longAnswerQuestionService.deleteById(questionId, session);
        return result;
    }

//    @PostMapping
//    public ResponseEntity<String> createNewQuestion(QuestionDTO questionDTO, HttpSession session){
//
//    }

//    @PutMapping("/{questionId}")
//    public ResponseEntity<String> updateQuestion(@PathVariable Long questionId, HttpSession session) {
//
//    }

    private MultipleChoiceQuestion convertToMultipleChoiceQuestionEntity(QuestionDTO multipleChoiceQuestionDTO) {
        return modelMapper.map(multipleChoiceQuestionDTO, MultipleChoiceQuestion.class);
    }

    private QuestionDTO convertToMultipleChoiceQuestionDTO(MultipleChoiceQuestion multipleChoiceQuestion) {
        return modelMapper.map(multipleChoiceQuestion, QuestionDTO.class);
    }

    private LongAnswerQuestion convertToLongAnswerQuestionEntity(QuestionDTO longAnswerQuestionDTO) {
        return modelMapper.map(longAnswerQuestionDTO, LongAnswerQuestion.class);
    }

    private QuestionDTO convertToLongAnswerQuestionDTO(LongAnswerQuestion longAnswerQuestion) {
        return modelMapper.map(longAnswerQuestion, QuestionDTO.class);
    }
}
