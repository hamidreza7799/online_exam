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
import java.util.Set;
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
        result.addAll(multipleChoiceQuestionService.findByOwnerTeacherId(session));
        result.addAll(longAnswerQuestionService.findByOwnerTeacherId(session));
        return result;
    }

    @GetMapping("/multiple-choice")
    public Set<MultipleChoiceQuestionDTO> getTeacherMultipleChoiceQuestions(HttpSession session) {
        //TODO RETURN ESSENTIAL EXCEPTION
        return multipleChoiceQuestionService.findByOwnerTeacherId(session);
    }

    @GetMapping("/long-answer")
    public Set<LongAnswerQuestionDTO> getTeacherLongAnswerQuestions(HttpSession session) {
        //TODO RETURN ESSENTIAL EXCEPTION
        return longAnswerQuestionService.findByOwnerTeacherId(session);
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
    public ResponseEntity<String> createNewMultipleChoiceQuestion(@RequestBody MultipleChoiceQuestionDTO questionDTO, HttpSession session){
        return multipleChoiceQuestionService.save(questionDTO, session);
    }

    @PostMapping("/long-answer")
    public ResponseEntity<String> createNewLongAnswerQuestion(@RequestBody LongAnswerQuestionDTO questionDTO, HttpSession session){
        return longAnswerQuestionService.save(questionDTO, session);
    }

    @PutMapping("/multiple-choice")
    public ResponseEntity<String> updateQuestion(@RequestBody MultipleChoiceQuestionDTO questionDTO, HttpSession session) {
        return multipleChoiceQuestionService.save(questionDTO, session);
    }

    @PutMapping("/long-answer")
    public ResponseEntity<String> updateQuestion(@RequestBody LongAnswerQuestionDTO questionDTO, HttpSession session) {
        return longAnswerQuestionService.save(questionDTO, session);
    }



}
