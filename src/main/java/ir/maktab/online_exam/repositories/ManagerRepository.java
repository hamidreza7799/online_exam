package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.repositories.custom.CustomManagerRepository;

public interface ManagerRepository extends UserRepository<Manager>, CustomManagerRepository {

}
