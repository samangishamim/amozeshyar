package repository.courseRepository;

import base.repository.BaseRepository;
import model.Course;
import model.Person;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends BaseRepository<Course, Long> {

void addCourse(Course course,Long professorId);

    Optional<List<Course>> findCourseByYearAndSemesterAndProfessorId(int year, int semester, Long professorId);
    Optional<List<Course>> findCourseByYearAndSemester(int year, int semester);
    boolean findCourseByIdOfCourse(int year, int semester, int idOfCourse);
}
