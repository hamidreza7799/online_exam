package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.DTO.QuestionDTO;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.repositories.QuestionRepository;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Set;

public interface QuestionService<E extends Question, DTO extends QuestionDTO> extends BaseService<E, QuestionRepository<E>> {

    Set<DTO> findByOwnerTeacherId(HttpSession session);

    ResponseEntity<String> save(DTO questionDTO, HttpSession session);

    ResponseEntity<String> deleteById(Long id, HttpSession session);

    E convertToEntity(DTO questionDTO);

    DTO convertToDTO(E question);
}
