package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.StudentAnswer;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StudentAnswerRepository<E extends StudentAnswer> extends BaseRepository<E> {


}
