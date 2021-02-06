package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.DTO.CreateExamQuestionDTO;
import ir.maktab.online_exam.domains.DTO.ExamQuestionDTO;
import ir.maktab.online_exam.services.ExamQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/exam-question")
public class ExamQuestionController {

    private final ExamQuestionService examQuestionService;

    public ExamQuestionController(ExamQuestionService examQuestionService) {
        this.examQuestionService = examQuestionService;
    }


    @PostMapping("/{examId}")
    public ResponseEntity<ExamQuestionDTO> addQuestionForExam(@PathVariable Long examId,
                                                              @RequestBody CreateExamQuestionDTO questionDTO, HttpSession session){

        return examQuestionService.save(examId, questionDTO, session);
    }

    @DeleteMapping("/{examQuestionId}")
    public ResponseEntity<String> deleteExamQuestion(@PathVariable Long examQuestionId, HttpSession session){
        return examQuestionService.deleteExamQuestionById(examQuestionId, session);
    }
}
