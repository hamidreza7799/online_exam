package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.domains.DTO.LongAnswerQuestionDTO;
import ir.maktab.online_exam.domains.DTO.QuestionDTO;
import ir.maktab.online_exam.domains.LongAnswerQuestion;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.repositories.LongAnswerQuestionRepository;
import ir.maktab.online_exam.services.LongAnswerQuestionService;
import ir.maktab.online_exam.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LongAnswerQuestionServiceImpl extends QuestionServiceImpl<LongAnswerQuestion, LongAnswerQuestionDTO>
        implements LongAnswerQuestionService {

    private final ModelMapper modelMapper;

    public LongAnswerQuestionServiceImpl(LongAnswerQuestionRepository repository, TeacherService teacherService, ModelMapper modelMapper) {
        super(repository, teacherService);
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<String> save(LongAnswerQuestionDTO questionDTO, HttpSession session) {
        LongAnswerQuestion question = this.convertToEntity(questionDTO);
        if(question.getAnswerText() == null || question.getAnswerText().isEmpty() || question.getAnswerText().isBlank())
            return ResponseEntity.badRequest().body("Text of answer should not empty or blank.");
        return super.save(question, session);
    }

    @Override
    public LongAnswerQuestion convertToEntity(LongAnswerQuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, LongAnswerQuestion.class);
    }

    @Override
    public LongAnswerQuestionDTO convertToDTO(LongAnswerQuestion question) {
        return modelMapper.map(question, LongAnswerQuestionDTO.class);
    }
}
