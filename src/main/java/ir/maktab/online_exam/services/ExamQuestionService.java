package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.DTO.ExamQuestionDTO;
import ir.maktab.online_exam.domains.ExamQuestion;
import ir.maktab.online_exam.repositories.ExamQuestionRepository;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Set;

public interface ExamQuestionService extends BaseService<ExamQuestion, ExamQuestionRepository> {

    Set<ExamQuestionDTO> findByExamId(Long examId);

    ResponseEntity<ExamQuestionDTO> save(Long examId, ExamQuestionDTO examQuestion, HttpSession session);

    ResponseEntity<String> deleteExamQuestionById(Long examQuestionId, HttpSession session);

    ExamQuestion convertToEntity(ExamQuestionDTO examQuestionDTO);

    ExamQuestionDTO convertToDTO(ExamQuestion examQuestion);
}
