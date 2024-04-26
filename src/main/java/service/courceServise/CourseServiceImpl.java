package service.courceServise;

import base.service.BaseServiceImpl;
import exception.NotFoundException;
import model.Course;
import model.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.courseRepository.CourseRepository;

import java.util.Optional;

public class CourseServiceImpl extends BaseServiceImpl<Course,Long,CourseRepository>implements CourseService{
    public CourseServiceImpl(CourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }




    @Override
    public void addCourse(Course course, Long professorId) {
        try(Session session=sessionFactory.getCurrentSession()){
            session.beginTransaction();
            repository.addCourse(course,professorId);
            session.getTransaction().commit();
        }catch (Exception e)
        {

        }
    }

    @Override
    public Optional<Long> professorSemesterSalary(Professor professor, int semester) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<Long> find = repository.professorSemesterSalary(professor,semester);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean findCourseByIdOfCourse(int year, int semester, int idOfCourse) {
        return repository.findCourseByIdOfCourse(year, semester, idOfCourse);
    }
}
