package service.employeeService;

import base.service.BaseService;
import model.Employee;
import repository.employessRepository.EmployeeRepositoryImpl;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends BaseService<Employee, Long> {
    public List<Employee> employeeSignIn(String nationalId , String password);
    public Optional<Employee> employeeSalary(String nationalId);
}
