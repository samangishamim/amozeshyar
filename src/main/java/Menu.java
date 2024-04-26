import service.courceServise.CourseService;
import service.employeeService.EmployeeService;
import service.professorService.ProfessorService;
import service.registerCourse.RegisterCourseService;
import service.studentService.StudentService;
import utill.ApplicationContext;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    final EmployeeService employeeService = ApplicationContext.getEmployeeService();
    final ProfessorService professorService = ApplicationContext.getProfessorService();
    final StudentService studentService = ApplicationContext.getStudentService();
    final CourseService courseService = ApplicationContext.getCourseService();
    final RegisterCourseService registerCourseService = ApplicationContext.getRegisterCourseService();



}
