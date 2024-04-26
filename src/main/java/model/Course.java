package model;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;




@Getter
@Setter
@AllArgsConstructor

@Entity(name = "course")
public class Course extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer units;

    @Column(name = "lesson_code", nullable = false)
    private Integer courseCode;

    @Column(name = "year")
    private  Integer year;

    @Column(nullable = false)
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


    public Course() {
    }
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", units=" + units +
                ", courseCode=" + courseCode +
                ", year=" + year +
                ", semester=" + semester +
                ", professor=" + professor +
                ", registerCourses=" + registerCourses +
                '}';
    }
}