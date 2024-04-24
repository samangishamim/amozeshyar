package repository.courseRepository;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.Course;
import model.Person;
import model.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.personRepository.PersonRepository;

public class CourseRepositoryImpl extends BaseRepositoryImpl<Course,Long> implements CourseRepository {


    public CourseRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }

    @Override
    public void addCourse(Course course, Long professorId) {
        Session session = sessionFactory.getCurrentSession();
        Professor professor = session.get(Professor.class, professorId);
        course.setProfessor(professor);
        if (course.getId()==null)
        session.persist(course);
        else
            session.merge(course);
    }
}
