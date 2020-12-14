package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Entity;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<E extends User> extends BaseRepository<E> {

    E findByUsername(String username);

    @EntityGraph(value = "getRoleInAuthorization", type = EntityGraph.EntityGraphType.FETCH)
    E findByVerificationIsTrueAndUsername(String username);

    List<E> findByVerificationIsFalse();

    Integer deleteByIdAndVerificationIsFalse(Long id);

    Optional<E> findByIdAndVerificationIsTrue(Long id);
}
