package model;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)

@Table(name = "persons")

@Entity
public class Person extends BaseEntity<Long>{


    @Column(name = "first_name" )
    private String firstName;

    @Column(name = "last_name" )
    private String lastName;

    @Column(name = "national_id", unique = true, nullable = false)
    private String nationalId;

    @Column(name = "password")
    private String password;

    public Person(Long aLong, String firstName, String lastName, String nationalId, String password) {
        super(aLong);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.password = password;
    }

    public Person(String firstName, String lastName, String nationalId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
