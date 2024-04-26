package repository.employessRepository;

import base.repository.BaseRepository;
import model.Employee;
import model.Person;
import repository.personRepository.PersonRepository;

import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    public Optional<Employee> employeeSignIn(String nationalId , String password);
    public Optional<Employee> employeeSalary(String nationalId);

}
