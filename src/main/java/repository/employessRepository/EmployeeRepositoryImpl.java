package repository.employessRepository;

import base.repository.BaseRepositoryImpl;
import connection.SessionFactorySingleton;
import model.Employee;
import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.personRepository.PersonRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class  EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee,Long> implements EmployeeRepository{

    public EmployeeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public String getMyClass() {
        return "Employee";
    }

    @Override
    public EmployeeRepositoryImpl.EmployeePay pay(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();


            Query<Employee> query = session.createQuery("FROM Employee e " +
                    "WHERE e.id = : employeeId", Employee.class);
            query.setParameter("employeeId", id);
            List<Employee> resultList = query.setParameter("employeeId", id).getResultList();
            return new EmployeePay(resultList.get(0).getFirstName(),
                    resultList.get(0).getLastName(),
                    resultList.get(0).getSalary());
        }
    }

    @Override
    public Optional<Employee> findByNationalId(String nationalId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query =
                session.createQuery("FROM Employee e WHERE e.nationalId = :theNationalId", Employee.class);
        List<Employee> resultList = query.setParameter("theNationalId", nationalId).getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(resultList.get(0));
    }

    @Override
    public boolean checkNationalIdAndPassword(String nationalId, String password) {
        SessionFactory factory = SessionFactorySingleton.getInstance();
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();

            Query<Person> query = session.createQuery("FROM Employee e " +
                    "WHERE e.nationalId = :nId AND e.password = :pass");
            query.setParameter("nId", nationalId);
            query.setParameter("pass", password);
            List<Person> resultList = query.getResultList();

            session.getTransaction().commit();
            return resultList.isEmpty();
        }    }

        public record EmployeePay(String firstName, String lastName, Double salary) {
        }
}
