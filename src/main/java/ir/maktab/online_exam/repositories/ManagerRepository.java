package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.User;

public interface ManagerRepository extends BaseRepository<Manager> {

    Manager findByUsername(String username);
}
