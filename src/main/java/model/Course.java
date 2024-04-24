package model;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity<Long> {


    private String name;
    private Integer units;
    private Integer courseCode;
    private  Integer year;
    private  Integer semester;


    public Course(Long aLong, String name, Integer units, Integer courseCode, Integer year, Integer semester) {
        super(aLong);
        this.name = name;
        this.units = units;
        this.courseCode = courseCode;
        this.year = year;
        this.semester = semester;
    }

    public Course(String name, Integer units, Integer courseCode, Integer year, Integer semester) {
        this.name = name;
        this.units = units;
        this.courseCode = courseCode;
        this.year = year;
        this.semester = semester;
    }

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<RegisterCourse> registerCourses;

}