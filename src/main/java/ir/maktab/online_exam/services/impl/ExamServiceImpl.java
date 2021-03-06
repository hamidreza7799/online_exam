package ir.maktab.online_exam.services.impl;

import ir.maktab.online_exam.base.service.impl.BaseServiceImpl;
import ir.maktab.online_exam.domains.DTO.ExamDTO;
import ir.maktab.online_exam.domains.Exam;
import ir.maktab.online_exam.repositories.ExamRepository;
import ir.maktab.online_exam.services.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl extends BaseServiceImpl<Exam, ExamRepository> implements ExamService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ExamRepository repository;
    private final ModelMapper modelMapper;

    public ExamServiceImpl(ExamRepository repository, ModelMapper modelMapper) {
        super(repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Exam save(Exam exam) {
        if(exam.getStartDateTime().isAfter(exam.getEndDateTime()) || exam.getEndDateTime().isEqual(exam.getStartDateTime()))
            //TODO RETURN EXCEPTION
            return null;
        if(Duration.between(LocalDateTime.now(), exam.getStartDateTime()).toMinutes() < 1)
            //TODO RETURN EXCEPTION, FIX DURATION BETWEEN START-TIME AND NOW
            return null;
        if(Duration.between(exam.getStartDateTime(), exam.getEndDateTime()).toMinutes() < exam.getTime())
            //TODO RETURN EXCEPTION
            return null;
        return repository.save(exam);
    }

    @Override
    public Optional<ExamDTO> findByExamId(Long id) {
        Optional<Exam> exam = super.findById(id);
        if(exam.isEmpty())
            return Optional.empty();
        else
            return Optional.of(this.convertToDTO(exam.get()));
    }

    @Override
    public List<ExamDTO> findByCourseId(Long id) {
        return repository.findByCourseId(id).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Exam convertToEntity(ExamDTO examDTO) {
        Exam exam = modelMapper.map(examDTO, Exam.class);
        exam.setStartDateTime(this.convertStringToDateTime(examDTO.getStartDateTime()));
        exam.setEndDateTime(this.convertStringToDateTime(examDTO.getEndDateTime()));
        return exam;
    }

    @Override
    public ExamDTO convertToDTO(Exam exam) {
        ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
        examDTO.setStartDateTime(this.convertDateTimeToString(exam.getStartDateTime()));
        examDTO.setEndDateTime(this.convertDateTimeToString(exam.getEndDateTime()));
        return examDTO;
    }

    private String convertDateTimeToString(LocalDateTime dateTime){
        String time = dateTime.format(formatter);
        return dateTime.format(formatter);
    }

    private LocalDateTime convertStringToDateTime(String dateTimeString){
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
