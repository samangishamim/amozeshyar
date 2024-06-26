package service.registerCourse;

import base.service.BaseServiceImpl;
import exception.NotFoundException;
import model.Course;
import model.RegisterCourse;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.registerCourse.RegisterCourseRepository;

import java.util.List;
import java.util.Optional;

public class RegisterCourseServiceImpl extends BaseServiceImpl<RegisterCourse, Long, RegisterCourseRepository>
        implements RegisterCourseService {
    public RegisterCourseServiceImpl(RegisterCourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<RegisterCourse> listStudentLessonsWithGrade(Student student) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<RegisterCourse> find = repository.listStudentLessonsWithGrade(student);
            session.getTransaction().commit();
            return find;
        }
    }

    @Override
    public Optional<RegisterCourse> checkDoublLessonInOneSemster(Student student, Course course) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<RegisterCourse> find = repository.checkDoublLessonInOneSemster(student, course);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public double getGPA(int year, int semester, Long studentId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            double gpa = repository.getGPA(year, semester, studentId);
            session.getTransaction().commit();
            return gpa;
        } catch (Exception ignored) {
        }
        return 0;
    }

    @Override
    public List<RegisterCourse> findByCourseId(long id) {
        try (Session session=sessionFactory.getCurrentSession()){
            session.beginTransaction();
           Optional<List<RegisterCourse> > optionalRegisterCourse =repository.findByCourseId(id);
           optionalRegisterCourse.orElseThrow(
                   () -> new NotFoundException(String.format("not found with id: %s " , id))
           );
           return optionalRegisterCourse.get();
        }
    }

    @Override
    public RegisterCourse findByStudentIdAndCourseId(Long studentId, Long courseId) {
        return repository.findByStudentIdAndCourseId(studentId, courseId);
    }
}

