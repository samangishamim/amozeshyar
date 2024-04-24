package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue(value = "Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student extends Person {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<RegisterCourse> registerCourses;


}