package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.Operation;
import ir.maktab.online_exam.repositories.OperationRepository;

public interface OperationService extends BaseService<Operation, OperationRepository> {

    Operation findByName(String name);
}
