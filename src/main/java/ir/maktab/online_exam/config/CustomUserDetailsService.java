package ir.maktab.online_exam.config;

import ir.maktab.online_exam.services.ManagerService;
import ir.maktab.online_exam.services.StudentService;
import ir.maktab.online_exam.services.TeacherService;
import ir.maktab.online_exam.domains.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final ManagerService managerService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CustomUserDetailsService(ManagerService managerService, TeacherService teacherService, StudentService studentService) {
        this.managerService = managerService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Override
    public UserDetails loadUserByUsername(String typeAndUsername) throws UsernameNotFoundException {
        if(typeAndUsername.length() < 8)
            //TODO Fix type of error
            throw new UsernameNotFoundException("username not Found!!!");
        User user;
        String type = typeAndUsername.substring(0,2);
        String username = typeAndUsername.substring(2);
        switch (type) {
            case "M_":
                user = managerService.findByUsername(username);
                break;
            case "T_":
                user = teacherService.findByUsername(username);
                break;
            case "S_":
                user = studentService.findByUsername(username);
                break;
            default:
                //TODO Fix type of error
                throw new UsernameNotFoundException("username not Found!!!");
        }
        if(user == null)
            throw new UsernameNotFoundException("username : " + username + " not Found!!!");
        else
            return new CustomUserDetails(user);
    }
}
