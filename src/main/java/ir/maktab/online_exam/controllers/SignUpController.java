package ir.maktab.online_exam.controllers;


import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.domains.User;
import ir.maktab.online_exam.services.ManagerService;
import ir.maktab.online_exam.services.StudentService;
import ir.maktab.online_exam.services.TeacherService;
import ir.maktab.online_exam.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {
    private final ManagerService managerService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public SignUpController(ManagerService managerService, TeacherService teacherService, StudentService studentService) {
        this.managerService = managerService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @PostMapping
    public String signUp(@RequestBody User receivedUser){
        //TODO return spring exception
        //TODO return essential message for uniq constraints like username
        System.out.println(receivedUser);
        User user;
        switch (receivedUser.getUserType()){
            case "M_": user = managerService.save(managerService.castUserToUserSubclass(receivedUser, Manager.class));break;
            case "T_": user = teacherService.save(teacherService.castUserToUserSubclass(receivedUser, Teacher.class));break;
            case "S_": user = studentService.save(studentService.castUserToUserSubclass(receivedUser, Student.class));break;
            default:return "Format of request is wrong";
        }
        return "success";
    }

}
