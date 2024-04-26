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
    public Optional<Employee> employeeSignIn(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql ="FROM %s WHERE username=:username AND password=:password ";
        Query<Employee> query = session.createQuery(String.format(hql , getMyClass()),getEntityClass());
        query.setParameter("username", username );
        query.setParameter( "password" , password );
        Object result = query.uniqueResult();

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Employee> employeeSalary(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("FROM Employee e  " +
                " WHERE e.nationalid=:nationalid" , Employee.class);
        query.setParameter("username", username );
        Employee employee = query.uniqueResult();

        return Optional.ofNullable(query.getSingleResult());
    }
}
