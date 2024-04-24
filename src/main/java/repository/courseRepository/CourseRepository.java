package repository.courseRepository;

import base.repository.BaseRepository;
import model.Course;
import model.Person;

public interface CourseRepository extends BaseRepository<Course, Long> {

void addCourse(Course course,Long professorId);
}
