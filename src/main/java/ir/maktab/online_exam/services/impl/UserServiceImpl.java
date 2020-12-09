package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.domains.User;
import ir.maktab.online_exam.repositories.ManagerRepository;
import ir.maktab.online_exam.repositories.UserRepository;
import ir.maktab.online_exam.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class UserServiceImpl<E extends User> extends BaseServiceImpl<E, UserRepository<E>>
        implements UserService<E> {

    private final UserRepository<E> repository;

    public UserServiceImpl(UserRepository<E> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public E findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public E findByVerificationIsTrueAndUsername(String username){
        return this.repository.findByVerificationIsTrueAndUsername(username);
    }

    @Override
    public E signUpNewUser(String userType, String username, String password, String firstName, String lastName, String email){
        //TODO: return spring exception, not object of user
        if(this.findByUsername(username) != null)
            return null;
        else {
            User newUser;
            switch (userType){
                case "M_":newUser = new Manager();break;

                case "T_": newUser = new Teacher();break;

                case "S_": newUser = new Student();break;

                default:return null;
            }
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            try{
                return this.save((E) newUser);
            }catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
                return null;
            }
        }
    }

    @Override
    public List<E> findByVerificationIsFalse() {
        return this.repository.findByVerificationIsFalse();
    }

    @Override
    public Boolean deleteByIdAndVerificationIsFalse(Long id) {
        return this.repository.deleteByIdAndVerificationIsFalse(id) == 1;
    }
}
