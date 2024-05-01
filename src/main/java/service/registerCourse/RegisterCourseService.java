package service.registerCourse;

import base.service.BaseService;
import model.Course;
import model.RegisterCourse;
import model.Student;

import java.util.List;
import java.util.Optional;

public interface RegisterCourseService extends BaseService<RegisterCourse , Long> {
    public List<RegisterCourse> listStudentLessonsWithGrade(Student student);
    public Optional<RegisterCourse> checkDoublLessonInOneSemster(Student student, Course course);
    double getGPA(int year, int semester, Long studentId);
}
