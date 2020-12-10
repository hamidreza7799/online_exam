package ir.maktab.online_exam.domains.DTO;

import ir.maktab.online_exam.domains.Course;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CourseDTO implements Serializable {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Long id;
    private String title;
    //TODO define technology column with limit choice
    //TODO define course teacher and students
    private String startDate;
    private String endDate;

    public LocalDate getStartDateConverted() throws DateTimeParseException{
        return LocalDate.parse(this.startDate, formatter);
    }

    public void setStartDate(LocalDate date){
        this.startDate = date.format(formatter);
    }

    public LocalDate getEndDateConverted() throws DateTimeParseException {
        return LocalDate.parse(this.endDate, formatter);
    }

    public void setEndDate(LocalDate date){
        this.endDate = date.format(formatter);
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
