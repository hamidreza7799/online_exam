package ir.maktab.online_exam.base.service;


import ir.maktab.online_exam.base.domain.BaseEntity;
import ir.maktab.online_exam.base.repository.BaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface BaseService<E extends BaseEntity, Repository extends BaseRepository<E>> {

    E save(E e);

    Optional<E> findById(Long id);

    void deleteById(Long id);

    Set<E> findAll();

    Set<E> findByIdsIn(Collection<Long> ids);
}
