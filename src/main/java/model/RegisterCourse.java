package model;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "register_courses")

public class RegisterCourse  extends BaseEntity<Long> {


    private double grade;


    @Enumerated(EnumType.STRING)
    @Column(name = "course_situation")
    private CourseGrade courseGrade;
    private Long studentId;
    private Long courseId;

    public RegisterCourse(Long aLong, double grade, Long studentId, Long courseId, Student student, Course course) {
        super(aLong);
        this.grade = grade;
        this.studentId = studentId;
        this.courseId = courseId;
        this.student = student;
        this.course = course;
    }

    public RegisterCourse(double grade, Long studentId, Long courseId, Student student, Course course) {
        this.grade = grade;
        this.studentId = studentId;
        this.courseId = courseId;
        this.student = student;
        this.course = course;
    }

    public RegisterCourse(double grade, CourseGrade courseGrade, Long studentId, Long courseId) {
        this.grade = grade;
        this.courseGrade = courseGrade;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @ManyToOne
    @JoinColumn(name="student_id",insertable = false,updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id",insertable = false,updatable = false)
    private Course course;

    public RegisterCourse(Long aLong, Double grade, CourseGrade courseGrade) {
        super(aLong);
        this.grade = grade;
        this.courseGrade = courseGrade;
    }

    @Override
    public String toString() {
        return "RegisterCourse{" +
                "grade=" + grade +
                ", courseGrade=" + courseGrade +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", student=" + student +
                ", course=" + course +
                '}'+ super.toString();
    }
}
