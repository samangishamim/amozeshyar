package repository.employessRepository;

import base.repository.BaseRepository;
import model.Employee;
import model.Person;
import repository.personRepository.PersonRepository;

import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    EmployeeRepositoryImpl.EmployeePay pay(Long id);
    Optional<Employee> findByNationalId(String nationalId);
    boolean checkNationalIdAndPassword(String nationalId, String password);

}
