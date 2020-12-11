package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.domains.DTO.UserSearchDTO;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;

import java.util.List;

public interface CustomStudentRepository {

    List<Student> studentAdvanceSearch(UserSearchDTO userSearchDTO);


}
