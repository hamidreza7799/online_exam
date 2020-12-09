package ir.maktab.online_exam.base.repository;


import ir.maktab.online_exam.base.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<E> extends JpaRepository<E, Long> {
}
