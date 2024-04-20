package service.studentService;

import base.service.BaseServiceImpl;
import model.Student;
import org.hibernate.SessionFactory;
import repository.studentRepository.StudentRepository;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService{
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Student saveOrUpdate(Student entity) {
        return null;
    }

    @Override
    public Student findById(Long aLong) {
        return null;
    }

    @Override
    public void delete(Student student) {

    }
}
