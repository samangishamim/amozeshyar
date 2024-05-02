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
        Query<RegisterCourse> query = session.createQuery("SELECT r FROM RegisterCourse r" +
                " WHERE r.studentId=:studentId", RegisterCourse.class);
        query.setParameter("studentId", student.getId());
        List<RegisterCourse> registerCourseList = query.getResultList();
        return registerCourseList;
    }

    @Override
    public Optional<RegisterCourse> checkDoublLessonInOneSemster(Student student, Course course) {
        Session session = sessionFactory.getCurrentSession();
        Query<RegisterCourse> query = session.createQuery("FROM RegisterCourse r " +
                " WHERE r.studentid=:student and r.courseid=:course", RegisterCourse.class);
        query.setParameter("student", student.getId());
        query.setParameter("course", course.getId());
        List<RegisterCourse> registerCourseList = query.getResultList();

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public double getGPA(int year, int semester, Long studentId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT (SUM(e.mark * c.units) / SUM(c.units))" +
                " FROM RegisterCourse e " +
                "JOIN e.course c " +
                "ON e.courseId = c.id " +
                "WHERE e.studentId = :sId ");
        query.setParameter("sId", studentId);
        List<Double> resultList = query.getResultList();
        return resultList.get(0);
    }
}
