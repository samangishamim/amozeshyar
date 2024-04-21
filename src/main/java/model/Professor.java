package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double paperUnit;
    private ProfessorType professorType;

    @OneToMany(mappedBy = "professor", cascade = { CascadeType.ALL })
    private List<Course> courseList = new ArrayList<>();
}
