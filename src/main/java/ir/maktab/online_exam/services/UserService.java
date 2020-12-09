package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.domains.User;
import ir.maktab.online_exam.repositories.TeacherRepository;
import ir.maktab.online_exam.repositories.UserRepository;

public interface UserService<E extends User> extends BaseService<E, UserRepository<E>> {

    E findByUsername(String username);

    E findByVerificationIsTrueAndUsername(String username);

    E signUpNewUser(String userType, String username, String password, String firstName, String lastName, String email);

    default E castUserToUserSubclass(User user, Class<E> destinationClass){
        User castUser;
        if(destinationClass.equals(Manager.class))
            castUser = new Manager();
        else if(destinationClass.equals(Teacher.class))
            castUser = new Teacher();
        else if(destinationClass.equals(Student.class))
            castUser = new Student();
        else
            return null;
        castUser.setId(user.getId());
        castUser.setUsername(user.getUsername());
        castUser.setPassword(user.getPassword());
        castUser.setFirstName(user.getFirstName());
        castUser.setLastName(user.getLastName());
        castUser.setEmail(user.getEmail());
        castUser.setVerification(user.getVerification());
        castUser.setUserType(user.getUserType());
        return (E) castUser;
    }
}
