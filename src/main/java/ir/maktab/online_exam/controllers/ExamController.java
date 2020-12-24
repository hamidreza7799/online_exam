package ir.maktab.online_exam.controllers;


import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.domains.DTO.ExamDTO;
import ir.maktab.online_exam.domains.Exam;
import ir.maktab.online_exam.services.CourseService;
import ir.maktab.online_exam.services.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final CourseService courseService;
    private final ExamService examService;
    private final ModelMapper modelMapper;

    public ExamController(CourseService courseService, ExamService examService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.examService = examService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{courseId}/new-exam")
    public ResponseEntity<String> createNewExam(@PathVariable Long courseId, @RequestBody ExamDTO examDTO){
        //TODO FIX EXCEPTIONS
        try{
            Exam exam = examService.convertToEntity(examDTO);
            Optional<Course> course = courseService.findById(courseId);
            if(course.isEmpty())
                return ResponseEntity.badRequest().body("Course id not found");
            else
                exam.setCourse(course.get());
            exam = examService.save(exam);
            if(exam != null)
                return ResponseEntity.ok(exam.getId().toString());
            else
                return ResponseEntity.badRequest().build();

        }catch (DateTimeParseException e){
            return ResponseEntity.badRequest().body("Format of times not true");
        }

    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<String> deleteExam(@PathVariable Long examId){
        //TODO FIX DELETE FOR RETURN BOOLEAN
        examService.deleteById(examId);
        if(examService.findById(examId).isEmpty())
            return ResponseEntity.ok("This id deleted:(" + examId + ")");
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{courseId}/{examId}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long courseId, @PathVariable Long examId){
        Optional<ExamDTO> examDTO = examService.findByExamId(examId);
        if(examDTO.isEmpty())
            return ResponseEntity.notFound().build();
        else
            //TODO WHY I AM GET COURSE ID?!
            return ResponseEntity.ok(examDTO.get());
    }

    @GetMapping("/{courseId}")
    public List<ExamDTO> getAllExams(@PathVariable Long courseId){
        //TODO change dto for all exams (return less information)
        return examService.findByCourseId(courseId);

    }
}
