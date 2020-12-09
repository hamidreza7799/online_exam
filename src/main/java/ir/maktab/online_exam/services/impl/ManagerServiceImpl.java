package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.repositories.ManagerRepository;
import ir.maktab.online_exam.services.ManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends UserServiceImpl<Manager> implements ManagerService {

    private final ManagerRepository repository;

    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
