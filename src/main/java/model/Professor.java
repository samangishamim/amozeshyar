package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "Professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Professor extends Person {
    private Double salary;

    @OneToMany(mappedBy = "professor", cascade = { CascadeType.ALL })
    private Set<Course> coursesTaught = new HashSet<>();
}
