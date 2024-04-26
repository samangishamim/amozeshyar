package repository.registerCourse;

import base.repository.BaseRepositoryImpl;
import model.Course;
import model.RegisterCourse;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class RegisterCourseRepositoryImpl extends BaseRepositoryImpl<RegisterCourse, Long>
        implements RegisterCourseRepository {
    public RegisterCourseRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<RegisterCourse> getEntityClass() {
        return RegisterCourse.class;
    }

    @Override
    public String getMyClass() {
        return "RegisterCourse";
    }


    @Override
    public List<RegisterCourse> listStudentLessonsWithGrade(Student student) {
        Session session = sessionFactory.getCurrentSession();
        Query<RegisterCourse> query = session.createQuery("FROM registercourse r" +
                " WHERE r.student_id=:student", RegisterCourse.class);
        query.setParameter("student", student);
        List<RegisterCourse> registerCourseList = query.getResultList();
        return registerCourseList;
    }

    @Override
    public Optional<RegisterCourse> checkDoublLessonInOneSemster(Student student, Course course) {
        Session session = sessionFactory.getCurrentSession();
        Query<RegisterCourse> query = session.createQuery("FROM registercourse r " +
                " WHERE r.studentid=:student and r.courseid=:course", RegisterCourse.class);
        query.setParameter("student", student);
        query.setParameter("course", course);
        List<RegisterCourse> registerCourseList = query.getResultList();

        return Optional.ofNullable(query.getSingleResult());
    }
}
