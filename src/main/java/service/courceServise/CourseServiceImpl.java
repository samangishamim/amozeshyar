package service.courceServise;

import base.service.BaseServiceImpl;
import exception.NotFoundException;
import model.Course;
import model.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.courseRepository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {
    public CourseServiceImpl(CourseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }


    @Override
    public void addCourse(Course course, Long professorId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            repository.addCourse(course, professorId);
            session.getTransaction().commit();
        } catch (Exception e) {

        }
    }

    @Override
    public double professorSemesterSalary(Long professorId, int semester) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            double salary = repository.professorSemesterSalary(professorId, semester);
            session.getTransaction().commit();
            return salary;
        } catch (Exception e) {
            return -1.0;
        }
    }

    @Override
    public List<Course> findBySemester(Integer semester) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<List<Course>> find = repository.findBySemester(semester);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find.get();
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<Course> findByProfessorIdAndSemester(Long id, int semester) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<List<Course>> optionalCourseList = repository.findByProfessorIdAndSemester(semester, id);
            optionalCourseList.orElseThrow(
                    () -> new NotFoundException(
                            String.format("Course/courses with semester: %s for professor id: %s not found",
                                    semester, id))
            );
           return optionalCourseList.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
