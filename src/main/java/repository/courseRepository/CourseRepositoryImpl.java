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
        return "course";
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
    public Optional<List<Course>> findByProfessorIdAndSemester( int semester, Long professorId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Course> query = session.createQuery("FROM course c " +
                "WHERE  c.semester = :semester ", Course.class);
        query.setParameter("semester", semester);
        List<Course> courseList = query.getResultList();

        List<Course> newList = new ArrayList<>();
        for (Course course : courseList) {
            if (Objects.equals(course.getProfessor().getId(), professorId)) {
                newList.add(course);
            }
        }
        return Optional.ofNullable(newList);
    }

    @Override
    public Optional<List<Course>> findCourseByYearAndSemester(int year, int semester) {
        Session session = sessionFactory.getCurrentSession();
        Query<Course> query = session.createQuery("FROM course c " +
                "WHERE  c.semester = :semester", Course.class);
        query.setParameter("year", year);
        query.setParameter("semester", semester);
        List<Course> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }

    @Override
    public double professorSemesterSalary(Long professorId, int semester) {
        Session session = sessionFactory.getCurrentSession();
        Professor professor = session.get(Professor.class, professorId);
        double sumOfUnit=0;
        for (Course course : professor.getCourseList()) {
            if (course.getSemester()==semester){
               sumOfUnit += course.getUnits();
            }
        }
        return professor.getBasicSalary()+ sumOfUnit*1000000;
    }

    @Override
    public Optional<List<Course>> findBySemester(Integer semester) {
        Session session = sessionFactory.getCurrentSession();
        Query<Course> query = session.createQuery("FROM  course c  " +
                " WHERE c.semester=:semester" , Course.class);
        query.setParameter("semester", semester );
        List<Course> resultList = query.getResultList();

        return Optional.ofNullable(resultList);
    }
}
