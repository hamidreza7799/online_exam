package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.Exam;
import ir.maktab.online_exam.repositories.ExamRepository;

import java.util.List;

public interface ExamService extends BaseService<Exam, ExamRepository> {

    List<Exam> findByCourseId(Long id);
}
