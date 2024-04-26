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
    @Column(nullable = false)
    private Double salary;


    public Employee(Long aLong, String firstName, String lastName, String username, String password) {
        super(aLong, firstName, lastName, username, password);
    }

    public Employee(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, username, password);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", id=" + id +
                '}';
    }
}
