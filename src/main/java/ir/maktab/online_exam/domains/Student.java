package ir.maktab.online_exam.domains;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_student")
public class Student extends User{
}