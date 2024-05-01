package model;

import base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter


@Entity
public class Employee extends Person {

    private Double salary;

    public Employee() {
    }

    public Employee(Long aLong, String firstName, String lastName, String nationalId, String password) {
        super(aLong, firstName, lastName, nationalId, password);
    }

    public Employee(String firstName, String lastName, String nationalId, String password) {
        super(firstName, lastName, nationalId, password);
    }

    public Employee(Long aLong, String firstName, String lastName, String nationalId, String password, Double salary) {
        super(aLong, firstName, lastName, nationalId, password);
        this.salary = salary;
    }
    public Employee(String firstName, String lastName, String nationalId, String password, Double salary) {
        super(firstName, lastName, nationalId, password);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString()+
                "salary=" + salary +
                '}';
    }
}
