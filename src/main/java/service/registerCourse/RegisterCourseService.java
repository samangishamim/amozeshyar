package service.registerCourse;

import model.Course;
import model.RegisterCourse;
import model.Student;

import java.util.List;
import java.util.Optional;

public interface RegisterCourseService {
    public List<RegisterCourse> listStudentLessonsWithGrade(Student student);
    public Optional<RegisterCourse> checkDoublLessonInOneSemster(Student student, Course course);
}
