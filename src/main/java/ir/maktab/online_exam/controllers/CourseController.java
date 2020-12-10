package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.Course;
import ir.maktab.online_exam.domains.DTO.CourseDTO;
import ir.maktab.online_exam.services.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
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
