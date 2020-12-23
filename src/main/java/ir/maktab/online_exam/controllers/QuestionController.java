package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.DTO.LongAnswerQuestionDTO;
import ir.maktab.online_exam.domains.DTO.MultipleChoiceQuestionDTO;
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
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final LongAnswerQuestionService longAnswerQuestionService;

    public QuestionController(ModelMapper modelMapper
            , MultipleChoiceQuestionService multipleChoiceQuestionService, LongAnswerQuestionService longAnswerQuestionService) {
        this.modelMapper = modelMapper;
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
    public List<MultipleChoiceQuestionDTO> getTeacherMultipleChoiceQuestions(HttpSession session) {
        //TODO RETURN ESSENTIAL EXCEPTION
        return multipleChoiceQuestionService
                .findByOwnerTeacherId(session)
                .stream()
                .map(this::convertToMultipleChoiceQuestionDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/long-answer")
    public List<LongAnswerQuestionDTO> getTeacherLongAnswerQuestions(HttpSession session) {
        //TODO RETURN ESSENTIAL EXCEPTION
        return longAnswerQuestionService
                        .findByOwnerTeacherId(session)
                        .stream()
                        .map(this::convertToLongAnswerQuestionDTO)
                        .collect(Collectors.toList());

    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId, HttpSession session){
        //TODO WRITE IN QUESTION SERVICE OR NO?
        ResponseEntity<String> result;
        result = multipleChoiceQuestionService.deleteById(questionId, session);
        if(result.getStatusCode().value() == 404)
            return longAnswerQuestionService.deleteById(questionId, session);
        else
            return result;
    }

    @PostMapping("/multiple-choice")
    public ResponseEntity<String> createNewMultipleChoiceQuestion(MultipleChoiceQuestionDTO questionDTO, HttpSession session){
        return multipleChoiceQuestionService.save(this.convertToMultipleChoiceQuestionEntity(questionDTO), session);
    }

    @PostMapping("/long-answer")
    public ResponseEntity<String> createNewLongAnswerQuestion(LongAnswerQuestionDTO questionDTO, HttpSession session){
        return longAnswerQuestionService.save(this.convertToLongAnswerQuestionEntity(questionDTO), session);
    }

    @PutMapping("/multiple-choice")
    public ResponseEntity<String> updateQuestion(MultipleChoiceQuestionDTO questionDTO, HttpSession session) {
        return multipleChoiceQuestionService.save(this.convertToMultipleChoiceQuestionEntity(questionDTO), session);
    }

    @PutMapping("/long-answer")
    public ResponseEntity<String> updateQuestion(LongAnswerQuestionDTO questionDTO, HttpSession session) {
        return longAnswerQuestionService.save(this.convertToLongAnswerQuestionEntity(questionDTO), session);
    }

    private MultipleChoiceQuestion convertToMultipleChoiceQuestionEntity(MultipleChoiceQuestionDTO multipleChoiceQuestionDTO) {
        return modelMapper.map(multipleChoiceQuestionDTO, MultipleChoiceQuestion.class);
    }

    private MultipleChoiceQuestionDTO convertToMultipleChoiceQuestionDTO(MultipleChoiceQuestion multipleChoiceQuestion) {
        return modelMapper.map(multipleChoiceQuestion, MultipleChoiceQuestionDTO.class);
    }

    private LongAnswerQuestion convertToLongAnswerQuestionEntity(LongAnswerQuestionDTO longAnswerQuestionDTO) {
        return modelMapper.map(longAnswerQuestionDTO, LongAnswerQuestion.class);
    }

    private LongAnswerQuestionDTO convertToLongAnswerQuestionDTO(LongAnswerQuestion longAnswerQuestion) {
        return modelMapper.map(longAnswerQuestion, LongAnswerQuestionDTO.class);
    }
}
