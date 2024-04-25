package model;

import base.entity.BaseEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Employee")
@Setter
@Getter
@NoArgsConstructor

public class Employee extends Person {
    private Double salary;


    public Employee(String firstName, String lastName, String username, String password, Double salary) {
        super(firstName, lastName, username, password);
        this.salary = salary;
    }

    public Employee(Double salary) {
        this.salary = salary;
    }
}
