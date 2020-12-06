package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.repositories.ManagerRepository;
import ir.maktab.online_exam.services.ManagerService;

public class ManagerServiceImpl extends BaseServiceImpl<Manager, ManagerRepository> implements ManagerService {

    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
    }
}
