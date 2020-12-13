package ir.maktab.online_exam.controllers;


import ir.maktab.online_exam.domains.DTO.ExamDTO;
import ir.maktab.online_exam.domains.Exam;
import ir.maktab.online_exam.services.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;
    private final ModelMapper modelMapper;

    public ExamController(ExamService examService, ModelMapper modelMapper) {
        this.examService = examService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<String> createNewExam(){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable Long id){
        //TODO FIX DELETE FOR RETURN BOOLEAN
        examService.deleteById(id);
        if(examService.findById(id).isEmpty())
            return ResponseEntity.ok("This id deleted:(" + id + ")");
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id){
        Optional<Exam> exam = examService.findById(id);
        if(exam.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(this.convertToDTO(exam.get()));
    }

    @GetMapping("/{courseId}")
    public List<ExamDTO> getAllExams(@PathVariable Long courseId){
        //TODO change dto for all exams (return less information)
        return examService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ExamDTO convertToDTO(Exam exam){
        return modelMapper.map(exam, ExamDTO.class);
    }
}
