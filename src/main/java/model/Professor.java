package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter

@Entity
public class Professor extends Person {


    @Column(name = "main_salary", nullable = false)
    private Double basicSalary;

    @Column(name = "pay_per_unit", nullable = false)
    private Double payPerUnit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfessorType professorType;

    public Professor(Long aLong,String firstName, String lastName, String username, String password, Double basicSalary,
                     Double payPerUnit, ProfessorType professorType) {
        super(firstName, lastName, username, password);
        this.basicSalary = basicSalary;
        this.payPerUnit = payPerUnit;
        this.professorType = professorType;
    }
    public Professor(String firstName, String lastName, String username, String password, Double basicSalary,
                     Double payPerUnit, ProfessorType professorType) {
        super(firstName, lastName, username, password);
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
