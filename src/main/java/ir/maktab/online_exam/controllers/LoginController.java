package ir.maktab.online_exam.controllers;

import ir.maktab.online_exam.domains.DTO.UserDTO;
import ir.maktab.online_exam.domains.User;
import ir.maktab.online_exam.services.ManagerService;
import ir.maktab.online_exam.services.StudentService;
import ir.maktab.online_exam.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping({"/login", "/"})
public class LoginController {

    private final ModelMapper modelMapper;
    private final ManagerService managerService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public LoginController(ModelMapper modelMapper, ManagerService managerService
            , TeacherService teacherService, StudentService studentService) {
        this.modelMapper = modelMapper;
        this.managerService = managerService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> loginProcess(HttpSession session) {
        //TODO LEARN ABOUT SESSION AND ENUMERATION OF STRING
        //Enumeration<String> attributes = session.getAttributeNames();
        UserDTO currentUserDTO;
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
            String role = authorities.get(0).toString();
            if (role.equals("ROLE_MANAGER")) {
                currentUserDTO = this.convertToDTO(
                        managerService
                                .findByUsername((String) authentication.getPrincipal()), "M_");
                session.setAttribute("userType", "MANAGER");
            }
            else if (role.equals("ROLE_TEACHER")){
                currentUserDTO = this.convertToDTO(
                        teacherService
                                .findByUsername((String) authentication.getPrincipal()), "T_");
                session.setAttribute("userType", "TEACHER");
            }
            else if (role.equals("ROLE_STUDENT")){
                currentUserDTO = this.convertToDTO(
                        studentService
                                .findByUsername((String) authentication.getPrincipal()), "S_");
                session.setAttribute("userType", "STUDENT");
            }else {
                return ResponseEntity.unprocessableEntity().build();
            }
            session.setAttribute("userId", currentUserDTO.getId());
            return ResponseEntity.ok(currentUserDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    private UserDTO convertToDTO(User user, String userType) {
        user.setUserType(userType);
        return modelMapper.map(user, UserDTO.class);
    }

}
