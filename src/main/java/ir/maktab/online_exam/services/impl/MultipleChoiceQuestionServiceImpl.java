package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.domains.DTO.MultipleChoiceQuestionDTO;
import ir.maktab.online_exam.domains.DTO.QuestionChoiceDTO;
import ir.maktab.online_exam.domains.DTO.QuestionDTO;
import ir.maktab.online_exam.domains.MultipleChoiceQuestion;
import ir.maktab.online_exam.domains.Question;
import ir.maktab.online_exam.domains.QuestionChoice;
import ir.maktab.online_exam.repositories.MultipleChoiceQuestionRepository;
import ir.maktab.online_exam.services.MultipleChoiceQuestionService;
import ir.maktab.online_exam.services.QuestionChoiceService;
import ir.maktab.online_exam.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MultipleChoiceQuestionServiceImpl extends QuestionServiceImpl<MultipleChoiceQuestion, MultipleChoiceQuestionDTO>
        implements MultipleChoiceQuestionService {

    private final ModelMapper modelMapper;
    private final QuestionChoiceService questionChoiceService;

    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository repository, TeacherService teacherService,
                                             ModelMapper modelMapper, QuestionChoiceService questionChoiceService) {
        super(repository, teacherService);
        this.modelMapper = modelMapper;
        this.questionChoiceService = questionChoiceService;
    }

    @Override
    public ResponseEntity<String> save(MultipleChoiceQuestionDTO questionDTO, HttpSession session) {
        MultipleChoiceQuestion question = this.convertToEntity(questionDTO);
        if(question.getQuestionChoices() == null || question.getQuestionChoices().size() == 0)
            return ResponseEntity.badRequest().body("Multiple choice question should have at least one choice.");
        question.getQuestionChoices().forEach(questionChoice -> questionChoice.setQuestion(question));
        return super.save(question, session);
    }

    @Override
    public MultipleChoiceQuestion convertToEntity(MultipleChoiceQuestionDTO questionDTO) {
        MultipleChoiceQuestion question = modelMapper.map(questionDTO, MultipleChoiceQuestion.class);
        Set<QuestionChoice> questionChoices = new HashSet<>();
        ((MultipleChoiceQuestionDTO) questionDTO)
                .getQuestionChoice()
                .forEach(questionChoiceDTO -> {
                    questionChoices.add(
                            questionChoiceService.convertToEntity(questionChoiceDTO)
                    );
                });
        question.setQuestionChoices(questionChoices);
        return question;
    }

    @Override
    public MultipleChoiceQuestionDTO convertToDTO(MultipleChoiceQuestion question) {
        MultipleChoiceQuestionDTO questionDTO = modelMapper.map(question, MultipleChoiceQuestionDTO.class);
        List<QuestionChoiceDTO> questionChoiceDTOS = new ArrayList<>();
        ((MultipleChoiceQuestion) question)
                .getQuestionChoices()
                .forEach(questionChoice -> {
                    questionChoiceDTOS.add(
                            questionChoiceService.convertToDTO(questionChoice)
                    );
                });
        questionDTO.setQuestionChoice(questionChoiceDTOS);
        questionDTO.setQuestionType("MultipleChoice");
        return questionDTO;
    }
}
