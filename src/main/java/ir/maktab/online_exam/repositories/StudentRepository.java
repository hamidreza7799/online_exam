package ir.maktab.online_exam.repositories;

import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.repositories.custom.CustomStudentRepository;

public interface StudentRepository extends UserRepository<Student>, CustomStudentRepository {

}
