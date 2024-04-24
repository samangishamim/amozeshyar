package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "Professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Professor extends Person {
    private Double basicSalary;
    private Double payPerUnit;
    private ProfessorType professorType;

    public Professor(String firstName, String lastName, String username, String password, Double basicSalary,
                     Double payPerUnit, ProfessorType professorType) {
        super(firstName, lastName, username, password);
        this.basicSalary = basicSalary;
        this.payPerUnit = payPerUnit;
        this.professorType = professorType;
    }

    public Professor(Double basicSalary, Double payPerUnit, ProfessorType professorType) {
        this.basicSalary = basicSalary;
        this.payPerUnit = payPerUnit;
        this.professorType = professorType;
    }

    @OneToMany(mappedBy = "professor", cascade = {CascadeType.ALL})
    private List<Course> courseList;

    public void addCourse(Course course) {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        courseList.add(course);
        course.setProfessor(this);
    }


}
