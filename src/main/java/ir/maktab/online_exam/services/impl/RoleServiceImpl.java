package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Role;
import ir.maktab.online_exam.repositories.RoleRepository;
import ir.maktab.online_exam.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleRepository> implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Role findByTitle(String title) {
        return repository.findByTitle(title);
    }
}
