package repository.courseRepository;

import base.repository.BaseRepository;
import model.Course;
import model.Person;
import model.Professor;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends BaseRepository<Course, Long> {

void addCourse(Course course,Long professorId);

    Optional<List<Course>> findCourseByYearAndSemesterAndProfessorId(int year, int semester, Long professorId);
    Optional<List<Course>> findCourseByYearAndSemester(int year, int semester);
    public Optional<Long> professorSemesterSalary(Professor professor, int semester);


    public Optional<Course> findByCourseCode(Integer courseCode);
}
