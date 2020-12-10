package ir.maktab.online_exam.domains;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_teacher")
public class Teacher extends User{
    //TODO CHECK CASCADE TYPES
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
