package repository.studentRepository;

import base.repository.BaseRepository;
import model.Employee;
import model.Person;
import model.RegisterCourse;
import model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends BaseRepository<Student,Long> {
    public Optional<List<Student>> studentSignIn(String studentId , String password);
    public Optional<List<Student>> studentInfo(String studentId);
    boolean checkNationalIdAndPassword(String nationalId, String password);
    Optional<List<RegisterCourse>> getListOfStudentCourse(Long studentId, int year, int semester);
}
