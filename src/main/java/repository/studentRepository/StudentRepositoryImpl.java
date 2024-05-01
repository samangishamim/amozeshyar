package repository.studentRepository;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.Employee;
import model.Person;
import model.RegisterCourse;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.personRepository.PersonRepository;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student,Long> implements StudentRepository {
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public String getMyClass() {
        return "Student";
    }

    @Override
    public Optional<List<Student>> studentSignIn(String nationalId, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql ="FROM Student s WHERE s.nationalId=:nationalId AND s.password=:password ";
        Query<Student> query = session.createQuery(hql , Student.class);
        query.setParameter("nationalId", nationalId );
        query.setParameter( "password" , password );
        List<Student> resultList = query.getResultList();

        return Optional.ofNullable(resultList);
    }

    @Override
    public Optional<List<Student>> studentInfo(String nationalId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("FROM Student s  " +
                " WHERE s.nationalId=:nationalId" , Student.class);
        query.setParameter("nationalId", nationalId );
        List<Student> resultList = query.getResultList();

        return Optional.ofNullable(resultList);
    }

    @Override
    public boolean checkNationalIdAndPassword(String nationalId, String password) {
        SessionFactory factory = SessionFactorySingleton.getInstance();
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();

            Query<Person> query = session.createQuery("FROM Student s " +
                    "WHERE s.nationalId = :nId AND s.password = :pass");
            query.setParameter("nId", nationalId);
            query.setParameter("pass", password);
            List<Person> resultList = query.getResultList();

            session.getTransaction().commit();
            return resultList.isEmpty();
        }
    }

    @Override
    public Optional<List<RegisterCourse>> getListOfStudentCourse(Long studentId, int year, int semester) {
        Session session = sessionFactory.getCurrentSession();
        Query<RegisterCourse> query = session.createQuery("FROM RegisterCourse e " +
                "JOIN e.course c " +
                "ON c.id = e.courseId " +
                "WHERE e.studentId = :sId AND " +
                "c.year = :year AND c.semester = :semester", RegisterCourse.class);
        query.setParameter("sId", studentId);
        query.setParameter("year", year);
        query.setParameter("semester", semester);
        List<RegisterCourse> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }
}
