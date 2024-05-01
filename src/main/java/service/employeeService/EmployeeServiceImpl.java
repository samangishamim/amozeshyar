package service.employeeService;

import base.service.BaseServiceImpl;
import connection.SessionFactorySingleton;
import exception.NotFoundException;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.employessRepository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long, EmployeeRepository> implements EmployeeService {
    public EmployeeServiceImpl(EmployeeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public List<Employee> employeeSignIn(String nationalId, String password) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<List<Employee>> find = repository.employeeSignIn(nationalId, password);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find.get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Employee> employeeSalary(String nationalId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<Employee> find = repository.employeeSalary(nationalId);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
