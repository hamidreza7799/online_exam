package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.domains.User;
import ir.maktab.online_exam.services.ManagerService;
import ir.maktab.online_exam.services.StudentService;
import ir.maktab.online_exam.services.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/new-register-requests")
public class NewRegisterRequestController {
    private final ManagerService managerService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public NewRegisterRequestController(ManagerService managerService, TeacherService teacherService
            , StudentService studentService) {
        this.managerService = managerService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<User> getNewRequests(){
        List<User> newRequests = new ArrayList<>();
        newRequests.addAll(managerService.findByVerificationIsFalse());
        newRequests.addAll(teacherService.findByVerificationIsFalse());
        newRequests.addAll(studentService.findByVerificationIsFalse());
        return newRequests;
    }

    @PostMapping("/confirm/{id}")
    public String confirmRequest(@PathVariable Long id){
        //TODO return exception
        Optional<Manager> manager = managerService.findById(id);
        if(manager.isPresent()){
            manager.get().setVerification(true);
            managerService.save(manager.get());
            return "Confirm " + manager.get().getUsername();
        }

        Optional<Teacher> teacher = teacherService.findById(id);
        if(teacher.isPresent()){
            teacher.get().setVerification(true);
            teacherService.save(teacher.get());
            return "Confirm " + teacher.get().getUsername();
        }

        Optional<Student> student = studentService.findById(id);
        if(student.isPresent()){
            student.get().setVerification(true);
            studentService.save(student.get());
            return "Confirm " + student.get().getUsername();
        }

        return "This id= " + id + " Not Exists";
    }

    @PostMapping("/reject/{id}")
    public String rejectRequest(@PathVariable Long id){
        //TODO return exception
        if(managerService.deleteByIdAndVerificationIsFalse(id)){
            return "Reject id= " + id;
        }
        if(teacherService.deleteByIdAndVerificationIsFalse(id)){
            return "Reject id= " + id;
        }
        if(studentService.deleteByIdAndVerificationIsFalse(id)){
            return "Reject id= " + id;
        }

        return "This id= " + id + " Not Exists";
    }

}
