package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student extends Person {
    private Double gpa;
    private Integer unitsTaken;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = { @JoinColumn(name = "STUDENT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") }
    )
    private Set<Course> courses = new HashSet<>();
}