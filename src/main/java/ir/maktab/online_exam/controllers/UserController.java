package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.DTO.UserDTO;
import ir.maktab.online_exam.domains.DTO.UserSearchDTO;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.domains.User;
import ir.maktab.online_exam.services.ManagerService;
import ir.maktab.online_exam.services.StudentService;
import ir.maktab.online_exam.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ManagerService managerService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public UserController(ManagerService managerService, TeacherService teacherService,
                          StudentService studentService, ModelMapper modelMapper) {
        this.managerService = managerService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        Optional<Student> student = studentService.findById(id);
        if(student.isPresent())
            return ResponseEntity.ok(convertToUserDto(student.get()));
        Optional<Teacher> teacher = teacherService.findById(id);
        if(teacher.isPresent())
            return ResponseEntity.ok(convertToUserDto(teacher.get()));
        Optional<Manager> manager = managerService.findById(id);
        if(manager.isPresent())
            return ResponseEntity.ok(convertToUserDto(manager.get()));
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public List<UserDTO> advanceSearch(@RequestBody UserSearchDTO userSearchDTO){
        List<UserDTO> result = new ArrayList<>();
        userSearchDTO.getUserTypes().forEach(
                type ->{
                    switch (type){
                        case "M_":result.addAll(
                                managerService.userAdvanceSearch(userSearchDTO)
                                .stream()
                                .map(this::convertToUserDto)
                                .collect(Collectors.toList())
                        );break;
                        case "S_":result.addAll(
                                studentService.userAdvanceSearch(userSearchDTO)
                                        .stream()
                                        .map(this::convertToUserDto)
                                        .collect(Collectors.toList())
                        );break;
                        case "T_":result.addAll(
                                teacherService.userAdvanceSearch(userSearchDTO)
                                        .stream()
                                        .map(this::convertToUserDto)
                                        .collect(Collectors.toList())
                        );break;
                    }
                }
        );
        return result;
    }

    private UserDTO convertToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
