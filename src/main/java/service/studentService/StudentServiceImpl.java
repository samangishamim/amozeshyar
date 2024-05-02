package service.studentService;

import base.service.BaseServiceImpl;
import connection.SessionFactorySingleton;
import exception.NotFoundException;
import model.RegisterCourse;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.studentRepository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService{
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public List<Student> studentSignIn(String nationalId, String password) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<List<Student>> find = repository.studentSignIn(nationalId, password);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Student> studentInfo(String nationalId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<Student> find = repository.studentInfo(nationalId);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<RegisterCourse> getListOfStudentCourse(Long studentId, int year, int semester) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Optional<List<RegisterCourse>> optionalEnrolmentList = repository.getListOfStudentCourse(studentId, year, semester);
            optionalEnrolmentList.orElseThrow(
                    () -> new NotFoundException(String.format(
                            "student with id: %s in term: %s - %s don't have any course.", studentId, year, semester))
            );

            List<RegisterCourse> registerCourseList = optionalEnrolmentList.get();
            session.getTransaction().commit();
            return registerCourseList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean checkNationalIdAndPassword(String nationalId, String password) {
        return repository.checkNationalIdAndPassword(nationalId, password);
    }
}
