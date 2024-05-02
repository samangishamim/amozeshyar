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
    public Optional<List<Employee>> employeeSignIn(String nationalId, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql ="FROM Employee e WHERE e.nationalId=:nationalId AND e.password=:password ";
        Query<Employee> query = session.createQuery(hql,Employee.class);
        query.setParameter("nationalId", nationalId );
        query.setParameter( "password" , password );
        List<Employee> resultList = query.getResultList();

        return Optional.ofNullable(resultList);
    }

    @Override
    public Optional<Employee> employeeSalary(String nationalId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("FROM Employee e  " +
                " WHERE e.nationalId=:nationalId" , Employee.class);
        query.setParameter("nationalId", nationalId );
        Employee employee = query.uniqueResult();

        return Optional.ofNullable(query.getSingleResult());
    }
}
