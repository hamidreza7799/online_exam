package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.Role;
import ir.maktab.online_exam.repositories.RoleRepository;

public interface RoleService extends BaseService<Role, RoleRepository> {

    Role findByTitle(String title);
}
