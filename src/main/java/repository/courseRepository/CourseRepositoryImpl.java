package repository.courseRepository;

import base.repository.BaseRepositoryImpl;
import model.Course;
import model.Person;
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
}
