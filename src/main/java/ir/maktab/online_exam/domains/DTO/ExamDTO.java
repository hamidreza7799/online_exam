package ir.maktab.online_exam.domains.DTO;

import ir.maktab.online_exam.domains.Course;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ExamDTO implements Serializable {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Long id;
    private String title;
    private String description;
    private Long time;
    private String startDateTime;
    private String endDateTime;

    public LocalDateTime getStartDateTimeConverted() throws DateTimeParseException {
        return LocalDateTime.parse(this.startDateTime, formatter);
    }

    public void setStartDateTime(LocalDateTime dateTime){
        this.startDateTime = dateTime.format(formatter);
    }

    public LocalDateTime getEndDateTimeConverted() throws DateTimeParseException {
        return LocalDateTime.parse(this.endDateTime, formatter);
    }

    public void setEndDateTime(LocalDateTime dateTime){
        this.endDateTime = dateTime.format(formatter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "ExamDTO{" +
                "formatter=" + formatter +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                '}';
    }
}
