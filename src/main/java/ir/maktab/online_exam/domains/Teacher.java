package ir.maktab.online_exam.domains;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_teacher")
public class Teacher extends User{
    //TODO CHECK CASCADE TYPES
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "ownerTeacher", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
