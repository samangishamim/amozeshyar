package repository.courseRepository;

import base.repository.BaseRepository;
import model.Course;
import model.Professor;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends BaseRepository<Course, Long> {

void addCourse(Course course,Long professorId);

    Optional<List<Course>> findByProfessorIdAndSemester( int semester, Long professorId);
    Optional<List<Course>> findCourseByYearAndSemester(int year, int semester);
    public Optional<Long> professorSemesterSalary(Professor professor, int semester);


    public Optional<List<Course>> findBySemester(Integer semester);
}
