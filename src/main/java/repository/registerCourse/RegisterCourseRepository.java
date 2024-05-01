package repository.registerCourse;

import base.repository.BaseRepository;
import model.Course;
import model.RegisterCourse;
import model.Student;

import java.util.List;
import java.util.Optional;

public interface RegisterCourseRepository extends BaseRepository<model.RegisterCourse, Long> {
    public List<RegisterCourse> listStudentLessonsWithGrade(Student student);

    public Optional<RegisterCourse> checkDoublLessonInOneSemster(Student student, Course course);
    double getGPA(int year, int semester, Long studentId);
}
