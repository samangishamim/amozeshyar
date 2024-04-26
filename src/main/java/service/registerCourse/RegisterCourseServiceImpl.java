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
}

