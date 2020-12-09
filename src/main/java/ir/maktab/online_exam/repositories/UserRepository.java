package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<E extends User> extends BaseRepository<E> {

    E findByUsername(String username);

    E findByVerificationIsTrueAndUsername(String username);

    List<E> findByVerificationIsFalse();

    Integer deleteByIdAndVerificationIsFalse(Long id);
}
