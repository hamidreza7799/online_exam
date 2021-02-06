package ir.maktab.online_exam.domains;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_exam")
//TODO WRITE GRAPH IN TAKE EXAM
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @NotEmpty @NotBlank
    private String title;
    @Column(name = "description")
    @Size(max = 4096)
    private String description;
    @Column(name = "time")
    @NotNull
    private Long time;
    @Column(name = "start_date")
    @NotNull
    private LocalDateTime startDateTime;
    @Column(name = "end_date")
    @NotNull
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_course")
    @NotNull
    private Course course;


    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @NotEmpty
    private Set<ExamQuestion> examQuestions = new HashSet<>();

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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(Set<ExamQuestion> examQuestionSet) {
        this.examQuestions = examQuestionSet;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", course=" + course +
                '}';
    }
}
