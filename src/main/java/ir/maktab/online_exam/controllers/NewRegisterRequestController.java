package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.*;
import ir.maktab.online_exam.domains.DTO.CourseDTO;
import ir.maktab.online_exam.domains.DTO.UserDTO;
import ir.maktab.online_exam.services.ManagerService;
import ir.maktab.online_exam.services.StudentService;
import ir.maktab.online_exam.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/new-register-requests")
public class NewRegisterRequestController {
    private final ModelMapper modelMapper;
    private final ManagerService managerService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public NewRegisterRequestController(ManagerService managerService, TeacherService teacherService
            , StudentService studentService, ModelMapper modelMapper) {
        this.managerService = managerService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserDTO> getNewRequests() {
        List<UserDTO> newRequests = new ArrayList<>();
        newRequests.addAll(managerService
                .findByVerificationIsFalse()
                .stream()
                .map(manager -> {
                    manager.setUserType("M_");
                    return convertToUserDto(manager);
                    })
                .collect(Collectors.toList()));
        newRequests.addAll(teacherService
                .findByVerificationIsFalse()
                .stream()
                .map(teacher -> {
                    teacher.setUserType("T_");
                    return convertToUserDto(teacher);
                })
                .collect(Collectors.toList()));
        newRequests.addAll(studentService
                .findByVerificationIsFalse()
                .stream()
                .map(student -> {
                    student.setUserType("S_");
                    return convertToUserDto(student);
                })
                .collect(Collectors.toList()));
        return newRequests;
    }

    @PostMapping("/confirm/{id}")
    public String confirmRequest(@PathVariable Long id) {
        //TODO return exception
        Optional<Manager> manager = managerService.findById(id);
        if (manager.isPresent()) {
            manager.get().setVerification(true);
            managerService.save(manager.get());
            return "Confirm " + manager.get().getUsername();
        }

        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isPresent()) {
            teacher.get().setVerification(true);
            teacherService.save(teacher.get());
            return "Confirm " + teacher.get().getUsername();
        }

        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            student.get().setVerification(true);
            studentService.save(student.get());
            return "Confirm " + student.get().getUsername();
        }

        return "This id= " + id + " Not Exists";
    }

    @DeleteMapping("/reject/{id}")
    @Transactional
    //TODO read about @transactional
    public String rejectRequest(@PathVariable Long id) {
        //TODO return exception
        if (managerService.deleteByIdAndVerificationIsFalse(id)) {
            return "Reject id= " + id;
        }
        if (teacherService.deleteByIdAndVerificationIsFalse(id)) {
            return "Reject id= " + id;
        }
        if (studentService.deleteByIdAndVerificationIsFalse(id)) {
            return "Reject id= " + id;
        }

        return "This id= " + id + " Not Exists";
    }

    private UserDTO convertToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
