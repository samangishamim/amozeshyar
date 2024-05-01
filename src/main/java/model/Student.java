package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter


@Table(name = "students")

@Entity

public class Student extends Person {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<RegisterCourse> registerCourses;

    public Student() {
    }

    public Student(Long aLong, String firstName, String lastName, String nationalid, String password, List<RegisterCourse> registerCourses) {
        super(aLong, firstName, lastName, nationalid, password);
        this.registerCourses = registerCourses;
    }

    public Student(String firstName, String lastName, String nationalid, String password, List<RegisterCourse> registerCourses) {
        super(firstName, lastName, nationalid, password);
        this.registerCourses = registerCourses;
    }
    public Student(String firstName, String lastName, String nationalid, String password) {
        super(firstName, lastName, nationalid, password);
    }

    public Student(Long aLong, String firstName, String lastName, String nationalid, String password) {
        super(aLong, firstName, lastName, nationalid, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}