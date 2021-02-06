package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends BaseRepository<Operation> {

    Operation findByName(String name);
}
