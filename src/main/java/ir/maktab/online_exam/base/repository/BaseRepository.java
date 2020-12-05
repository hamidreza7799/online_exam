package ir.maktab.online_exam.base.repository;


import ir.maktab.online_exam.base.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
}
