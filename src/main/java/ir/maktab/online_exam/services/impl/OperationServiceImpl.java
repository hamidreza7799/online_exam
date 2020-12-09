package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Operation;
import ir.maktab.online_exam.repositories.OperationRepository;
import ir.maktab.online_exam.services.OperationService;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl extends BaseServiceImpl<Operation, OperationRepository> implements OperationService {

    private final OperationRepository repository;

    public OperationServiceImpl(OperationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Operation findByName(String name) {
        return repository.findByName(name);
    }
}
