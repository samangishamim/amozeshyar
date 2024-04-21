package repository.employessRepository;

import base.repository.BaseRepositoryImpl;
import model.Employee;
import model.Person;
import org.hibernate.SessionFactory;
import repository.personRepository.PersonRepository;

public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee,Long> implements EmployeeRepository{

    public EmployeeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return null;
    }
}
