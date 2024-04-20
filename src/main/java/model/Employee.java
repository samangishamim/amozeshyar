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
@AllArgsConstructor
@NoArgsConstructor

public class Employee extends Person {
    private Double salary;
    private String role;

}
