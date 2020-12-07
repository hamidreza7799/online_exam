package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.repositories.ManagerRepository;

public interface ManagerService extends BaseService<Manager, ManagerRepository> {

    Manager findByUsername(String username);
}
