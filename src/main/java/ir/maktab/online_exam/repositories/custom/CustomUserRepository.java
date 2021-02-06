package ir.maktab.online_exam.repositories.custom;

import ir.maktab.online_exam.domains.DTO.UserSearchDTO;
import ir.maktab.online_exam.domains.User;

import java.util.List;

public interface CustomUserRepository {

    List<User> userAdvanceSearch(UserSearchDTO userSearchDTO);
}
