package repository.employessRepository;

import base.repository.BaseRepository;
import model.Employee;
import model.Person;
import org.w3c.dom.stylesheets.LinkStyle;
import repository.personRepository.PersonRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    public Optional<List<Employee>> employeeSignIn(String nationalId , String password);
    public Optional<Employee> employeeSalary(String nationalId);

}
