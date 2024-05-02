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




    @Column(name = "main_salary")
    private Double basicSalary;

    @Column(name = "pay_per_unit")
    private Double payPerUnit;

    @Column(name = "number_of_semesters")
    private Integer numberOfSemesters;

    @Enumerated(EnumType.STRING)
    private ProfessorType professorType;

    public Professor(Long aLong, String firstName, String lastName, String nationalid, String password, Double basicSalary,
                     Double payPerUnit, Integer numberOfSemesters, ProfessorType professorType) {
        super(aLong, firstName, lastName, nationalid, password);
        this.basicSalary = basicSalary;
        this.payPerUnit = payPerUnit;
        this.numberOfSemesters = numberOfSemesters;
        this.professorType = professorType;
    }

    public Professor(String firstName, String lastName, String nationalid, String password, Double basicSalary,
                     Double payPerUnit, Integer numberOfSemesters, ProfessorType professorType) {
        super(firstName, lastName, nationalid, password);
        this.basicSalary = basicSalary;
        this.payPerUnit = payPerUnit;
        this.numberOfSemesters = numberOfSemesters;
        this.professorType = professorType;
    }

    @OneToMany(mappedBy = "professor", cascade = {CascadeType.ALL})
    private List<Course> courseList;



    public Professor() {
    }

    public Professor(Long aLong, String firstName, String lastName, String nationalId, String password) {
        super(aLong, firstName, lastName, nationalId, password);
    }

    public Professor(String firstName, String lastName, String nationalId, String password) {
        super(firstName, lastName, nationalId, password);
    }

    public void addCourse(Course course) {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        courseList.add(course);
        course.setProfessor(this);
    }

    @Override
    public String toString() {
        return "Professor{" +
                super.toString()+
                "basicSalary=" + basicSalary +
                ", payPerUnit=" + payPerUnit +
                ", numberOfSemesters=" + numberOfSemesters +
                ", professorType=" + professorType +
                '}';
    }
}
