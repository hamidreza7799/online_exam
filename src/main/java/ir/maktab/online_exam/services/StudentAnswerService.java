package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.StudentAnswer;
import ir.maktab.online_exam.repositories.StudentAnswerRepository;

public interface StudentAnswerService<E extends StudentAnswer> extends BaseService<E, StudentAnswerRepository<E>> {
}
