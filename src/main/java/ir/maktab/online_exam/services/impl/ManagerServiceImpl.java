package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.repositories.ManagerRepository;
import ir.maktab.online_exam.services.ManagerService;
import ir.maktab.online_exam.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends UserServiceImpl<Manager> implements ManagerService {

    private final ManagerRepository repository;
    private final RoleService roleService;

    public ManagerServiceImpl(ManagerRepository repository, RoleService roleService) {
        super(repository);
        this.repository = repository;
        this.roleService = roleService;
    }

    @Override
    public Manager save(Manager manager) {
        manager.getRoles().add(
                roleService.findByTitle("MANAGER")
        );
        return super.save(manager);
    }
}
