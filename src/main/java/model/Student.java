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


    public Student(String firstName, String lastName, String username, String password, List<RegisterCourse> registerCourses) {
        super(firstName, lastName, username, password);
        this.registerCourses = registerCourses;
    }

    public Student(Long aLong, String firstName, String lastName, String username, String password, List<RegisterCourse> registerCourses) {
        super(aLong, firstName, lastName, username, password);
        this.registerCourses = registerCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "registerCourses=" + registerCourses +
                ", id=" + id +
                '}';
    }
}