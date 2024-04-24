import connection.SessionFactorySingleton;
import model.Course;
import model.Professor;
import model.ProfessorType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.courceServise.CourseService;
import service.professorService.ProfessorService;
import utill.ApplicationContext;

public class Main {
    public static void main(String[] args) {

//        Professor professor = new Professor("shahin", "samangi", "samangi2", "12345"
//                , 50000.0, 200.0, ProfessorType.LECTURER);
//        ProfessorService professorService = ApplicationContext.getProfessorService();
//        professorService.saveOrUpdate(professor);

        Course math = new Course("math", 4, 123, 1402, 1);
        CourseService courseService = ApplicationContext.getCourseService();
        courseService.saveOrUpdate(math);

        SessionFactory factory = SessionFactorySingleton.getInstance();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Professor professor = session.get(Professor.class, 1L);
        math.setProfessor(professor);
        session.persist(math);
        session.getTransaction().commit();


    }
}
