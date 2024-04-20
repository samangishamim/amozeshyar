package service.courceServise;

import base.service.BaseServiceImpl;
import model.Course;
import org.hibernate.SessionFactory;
import repository.courseRepository.CourseRepository;

public class CourseServiceImpl extends BaseServiceImpl<Course,Long,CourseRepository>implements CourseService{
    public CourseServiceImpl(CourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Course saveOrUpdate(Course entity) {
        return null;
    }

    @Override
    public Course findById(Long aLong) {
        return null;
    }

    @Override
    public void delete(Course course) {

    }
}
