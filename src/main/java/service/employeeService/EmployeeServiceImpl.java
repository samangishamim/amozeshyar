package service.employeeService;

import base.service.BaseServiceImpl;
import model.Employee;
import org.hibernate.SessionFactory;
import repository.employessRepository.EmployeeRepository;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long, EmployeeRepository> implements EmployeeService{
    public EmployeeServiceImpl(EmployeeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
