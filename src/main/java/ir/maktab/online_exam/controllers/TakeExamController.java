package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.DTO.ExamQuestionDTO;
import ir.maktab.online_exam.domains.DTO.MultipleChoiceStudentAnswerDTO;
import ir.maktab.online_exam.domains.ExamQuestion;
import ir.maktab.online_exam.domains.MultipleChoiceStudentAnswer;
import ir.maktab.online_exam.services.ExamQuestionService;
import ir.maktab.online_exam.services.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/take-exam")
public class TakeExamController {

    private final ExamService examService;

    public TakeExamController(ExamService examService){
        this.examService = examService;
    }

    @GetMapping("/{examId}")
    public ResponseEntity<List<ExamQuestionDTO>> examQuestions(@PathVariable Long examId, HttpSession session){
        return examService.examQuestions(examId, session);
    }

//    @PostMapping("/multiple-choice")
//    public ResponseEntity<String> storeStudentAnswer(@RequestBody MultipleChoiceStudentAnswerDTO studentAnswerDTO, HttpSession session){
//
//    }
}
