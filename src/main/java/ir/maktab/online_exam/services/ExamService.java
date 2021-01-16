package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.DTO.ExamDTO;
import ir.maktab.online_exam.domains.DTO.ExamQuestionDTO;
import ir.maktab.online_exam.domains.Exam;
import ir.maktab.online_exam.domains.ExamQuestion;
import ir.maktab.online_exam.repositories.ExamRepository;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface ExamService extends BaseService<Exam, ExamRepository> {

    Optional<ExamDTO> findByExamId(Long id);

    ResponseEntity<List<ExamQuestionDTO>> examQuestions(Long examId, HttpSession session);

    List<ExamDTO> findByCourseId(Long id);

    Exam convertToEntity(ExamDTO examDTO);

    ExamDTO convertToDTO(Exam exam);
}
