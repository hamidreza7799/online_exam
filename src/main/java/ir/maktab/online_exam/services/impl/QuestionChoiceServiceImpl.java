package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.DTO.QuestionChoiceDTO;
import ir.maktab.online_exam.domains.QuestionChoice;
import ir.maktab.online_exam.repositories.QuestionChoiceRepository;
import ir.maktab.online_exam.services.QuestionChoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestionChoiceServiceImpl extends BaseServiceImpl<QuestionChoice, QuestionChoiceRepository>
        implements QuestionChoiceService {

    private final ModelMapper modelMapper;
    private final QuestionChoiceRepository repository;

    public QuestionChoiceServiceImpl(ModelMapper modelMapper, QuestionChoiceRepository repository) {
        super(repository);
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public QuestionChoice convertToEntity(QuestionChoiceDTO questionChoiceDTO) {
        return modelMapper.map(questionChoiceDTO, QuestionChoice.class);
    }

    @Override
    public QuestionChoiceDTO convertToDTO(QuestionChoice questionChoice) {
        return modelMapper.map(questionChoice, QuestionChoiceDTO.class);
    }
}
