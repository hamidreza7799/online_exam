package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.base.repository.BaseRepository;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends UserRepository<Student>, CustomStudentRepository {

}
