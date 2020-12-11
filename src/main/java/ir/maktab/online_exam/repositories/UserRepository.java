package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface UserRepository<E extends User> extends BaseRepository<E> {

    E findByUsername(String username);

    E findByVerificationIsTrueAndUsername(String username);

    List<E> findByVerificationIsFalse();

    Integer deleteByIdAndVerificationIsFalse(Long id);
}
