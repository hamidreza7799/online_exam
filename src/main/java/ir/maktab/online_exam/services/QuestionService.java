package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.repositories.QuestionRepository;

import java.util.Set;

public interface QuestionService extends BaseService<Question, QuestionRepository> {

    Set<Question> findByOwnerTeacherId(Long ownerId);
}
