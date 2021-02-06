package ir.maktab.online_exam.repositories.custom;

import ir.maktab.online_exam.domains.DTO.UserSearchDTO;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Teacher;

import java.util.List;

public interface CustomTeacherRepository {
    List<Teacher> teacherAdvanceSearch(UserSearchDTO userSearchDTO);
}
