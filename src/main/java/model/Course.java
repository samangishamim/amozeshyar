package model;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;




@Getter
@Setter
@AllArgsConstructor


@Entity(name = "course")
public class Course extends BaseEntity<Long> {


    private String name;

    private Integer units;

    @Column(name = "lesson_code")
    private Integer courseCode;


    private Integer capacity;


    private  Integer semester;


    public Course(Long aLong, String name, Integer units, Integer courseCode, Integer capacity, Integer semester) {
        super(aLong);
        this.name = name;
        this.units = units;
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.semester = semester;
    }

    public Course(String name, Integer units, Integer courseCode, Integer capacity, Integer semester) {
        this.name = name;
        this.units = units;
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.semester = semester;
    }
    public Course(Long aLong, String name, Integer units, Integer courseCode,  Integer capacity,Integer semester, Professor professor) {
        super(aLong);
        this.name = name;
        this.units = units;
        this.courseCode = courseCode;
        this.semester = semester;
        this.capacity = capacity;
        this.professor = professor;
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
                ", capacity=" + capacity +
                ", semester=" + semester +
                ", professor=" + professor +
                ", registerCourses=" + registerCourses +
                '}';
    }
}