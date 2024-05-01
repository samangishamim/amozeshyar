package repository.courseRepository;

import base.repository.BaseRepositoryImpl;
import model.Course;
import model.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CourseRepositoryImpl extends BaseRepositoryImpl<Course, Long> implements CourseRepository {


    public CourseRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }


    @Override
    public String getMyClass() {
        return "Course";
    }

    @Override
    public void addCourse(Course course, Long professorId) {
        Session session = sessionFactory.getCurrentSession();
        Professor professor = session.get(Professor.class, professorId);
        course.setProfessor(professor);
        if (course.getId() == null)
            session.persist(course);
        else
            session.merge(course);
    }

    @Override
    public Optional<List<Course>> findCourseByYearAndSemesterAndProfessorId(int year, int semester, Long professorId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Course> query = session.createQuery("FROM Course c " +
                "WHERE c.year = :year AND c.semester = :semester", Course.class);
        query.setParameter("year", year);
        query.setParameter("semester", semester);
        List<Course> resultList = query.getResultList();
        List<Course> newList = new ArrayList<>();
        for (Course course : resultList) {
            if (Objects.equals(course.getProfessor().getId(), professorId)) {
                newList.add(course);
            }
        }
        return Optional.ofNullable(newList);
    }

    @Override
    public Optional<List<Course>> findCourseByYearAndSemester(int year, int semester) {
        Session session = sessionFactory.getCurrentSession();
        Query<Course> query = session.createQuery("FROM Course c " +
                "WHERE c.year = :year AND c.semester = :semester", Course.class);
        query.setParameter("year", year);
        query.setParameter("semester", semester);
        List<Course> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }

    @Override
    public Optional<Long> professorSemesterSalary(Professor professor, int semester) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("select sum (c.units) FROM Course c\n" +
                "                 WHERE c.professor_id=:professor AND c.semester=:semester"+Long.class);
        query.setParameter("professor",professor);
        query.setParameter("semester",semester);

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Course> findByCourseCode(Integer courseCode) {
        Session session = sessionFactory.getCurrentSession();
        Query<Course> query = session.createQuery("FROM  Course c  " +
                " WHERE c.courseCode=:courseCodee" , Course.class);
        query.setParameter("courseCode", courseCode );
        Course course = query.uniqueResult();

        return Optional.ofNullable(query.getSingleResult());
    }
}
