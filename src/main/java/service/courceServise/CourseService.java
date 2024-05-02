package service.courceServise;

import base.service.BaseService;
import model.Course;
import model.Professor;

import java.util.List;
import java.util.Optional;

public interface CourseService extends BaseService<Course, Long> {

    void addCourse(Course course,Long professorId);
    public Optional<Long> professorSemesterSalary(Professor professor , int semester);
    public List<Course> findBySemester(Integer semester);

   List <Course> findByProfessorIdAndSemester(Long id, int semester);
}
