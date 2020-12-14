package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.domains.DTO.CourseDTO;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.services.CourseService;
import ir.maktab.online_exam.services.StudentService;
import ir.maktab.online_exam.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, TeacherService teacherService
            , StudentService studentService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<String> registerNewCourse(@RequestBody CourseDTO courseDTO){
        try {
            Course course = convertToEntity(courseDTO);
            courseService.save(course);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(course.getId()).toUri();
            return ResponseEntity.ok()
                    .header(HttpHeaders.LOCATION, location.toString())
                    .body("Course add: id= " + course.getId());
        }catch (DateTimeParseException e){
            return ResponseEntity.badRequest().body("Format of date is not true");
        }

    }

    @PostMapping("/{courseId}/teachers/{teacherId}")
    public ResponseEntity<String> registerTeacherForCourse(@PathVariable Long courseId, @PathVariable Long teacherId){
        Optional<Course> course = courseService.findById(courseId);
        if(course.isEmpty())
            return ResponseEntity.badRequest().body("Course id not found");
        Optional<Teacher> teacher = teacherService.findByIdAndVerificationIsTrue(teacherId);
        if(teacher.isEmpty())
            return ResponseEntity.badRequest().body("Teacher id not found");

        course.get().setTeacher(teacher.get());
        if(courseService.save(course.get()) != null)
            return ResponseEntity.ok("Teacher set for course");
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Something is wrong");
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> registerStudentForCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        Optional<Course> course = courseService.findById(courseId);
        if(course.isEmpty())
            return ResponseEntity.badRequest().body("Course id not found");
        Optional<Student> student = studentService.findByIdAndVerificationIsTrue(studentId);
        if(student.isEmpty())
            return ResponseEntity.badRequest().body("Student id not found");

        course.get().getStudents().add(student.get());
        if(courseService.save(course.get()) != null)
            return ResponseEntity.ok("Student set for course");
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Something is wrong");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        try {
            courseService.deleteById(id);
            return ResponseEntity.ok()
                    .body("Course delete: id= " + id);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.badRequest().body("This id not exists");
        }catch (Exception e){return ResponseEntity.badRequest().body("Something is wrong");}
    }

    //TODO permit get courses for all users
    @GetMapping
    public List<CourseDTO> sendAllCourses(){
        return courseService.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CourseDTO convertToDto(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO courseDTO) throws DateTimeParseException {
        courseDTO.getStartDateConverted();
        courseDTO.getEndDateConverted();
        return modelMapper.map(courseDTO, Course.class);
    }
}
