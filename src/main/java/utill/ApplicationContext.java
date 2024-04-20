package utill;

import base.connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.courseRepository.CourseRepository;
import repository.courseRepository.CourseRepositoryImpl;
import repository.employessRepository.EmployeeRepository;
import repository.employessRepository.EmployeeRepositoryImpl;
import repository.personRepository.PersonRepository;
import repository.personRepository.PersonRepositoryImpl;
import repository.professorRepository.ProfessorRepository;
import repository.professorRepository.ProfessorRepositoryImpl;
import repository.studentRepository.StudentRepository;
import service.courceServise.CourseService;
import service.courceServise.CourseServiceImpl;
import service.employeeService.EmployeeService;
import service.employeeService.EmployeeServiceImpl;
import service.personService.PersonService;
import service.personService.PersonServiceImpl;
import service.professorService.ProfessorService;
import service.professorService.ProfessorServiceImpl;
import service.studentService.StudentService;

public class ApplicationContext {

    static final SessionFactory SESSION_FACTORY;

    private static final CourseRepository COURSE_REPOSITORY;
    private static final CourseService COURSE_SERVICE;
    private static final EmployeeRepository EMPLOYEE_REPOSITORY;
    private static final EmployeeService EMPLOYEE_SERVICE;

    private static final PersonRepository PERSON_REPOSITORY;
    private static final PersonService PERSON_SERVICE;

    private static final ProfessorRepository PROFESSOR_REPOSITORY;
    private static final ProfessorService PROFESSOR_SERVICE;

    private static final StudentRepository STUDENT_REPOSITORY;
    private static final StudentService STUDENT_SERVICE;

    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();

        COURSE_REPOSITORY = new CourseRepositoryImpl(SESSION_FACTORY);
        COURSE_SERVICE = new CourseServiceImpl(COURSE_REPOSITORY, SESSION_FACTORY);

        EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpl(SESSION_FACTORY);
        EMPLOYEE_SERVICE = new EmployeeServiceImpl(EMPLOYEE_REPOSITORY, SESSION_FACTORY);

        PERSON_REPOSITORY = new PersonRepositoryImpl(SESSION_FACTORY);
        PERSON_SERVICE = new PersonServiceImpl(PERSON_REPOSITORY, SESSION_FACTORY);

        PROFESSOR_REPOSITORY = new ProfessorRepositoryImpl(SESSION_FACTORY);
        PROFESSOR_SERVICE = new ProfessorServiceImpl(PROFESSOR_REPOSITORY, SESSION_FACTORY);

        STUDENT_REPOSITORY = new CourseRepositoryImpl(SESSION_FACTORY);
        STUDENT_SERVICE = new CourseServiceImpl(STUDENT_REPOSITORY, SESSION_FACTORY);

    }

    public static CourseService getCourseService() {
        return COURSE_SERVICE;
    }
    public static EmployeeService getEmployeeService() {
        return EMPLOYEE_SERVICE;
    }

    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }

    public static ProfessorService getProfessorService() {
        return PROFESSOR_SERVICE;
    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }


}

