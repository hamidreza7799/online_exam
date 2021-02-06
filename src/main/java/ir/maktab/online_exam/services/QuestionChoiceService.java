package ir.maktab.online_exam.services;

import ir.maktab.online_exam.base.service.BaseService;
import ir.maktab.online_exam.domains.DTO.QuestionChoiceDTO;
import ir.maktab.online_exam.domains.QuestionChoice;
import ir.maktab.online_exam.repositories.QuestionChoiceRepository;

public interface QuestionChoiceService extends BaseService<QuestionChoice, QuestionChoiceRepository> {

    QuestionChoice convertToEntity(QuestionChoiceDTO questionChoiceDTO);

    QuestionChoiceDTO convertToDTO(QuestionChoice questionChoice);
}
