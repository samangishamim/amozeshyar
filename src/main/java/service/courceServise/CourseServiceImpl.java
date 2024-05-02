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
    public List<Course> findCoursesByYearAndSemesterAndProfessorId(int year, int semester, Long professorId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<List<Course>> optionalCourseList =
                    repository.findCourseByYearAndSemesterAndProfessorId(year, semester, professorId);
            optionalCourseList.orElseThrow(
                    () -> new NotFoundException(
                            String.format("Course/courses with year: %s semester: %s for professor id: %s not found",
                                    year, semester, professorId))
            );
            return optionalCourseList.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
