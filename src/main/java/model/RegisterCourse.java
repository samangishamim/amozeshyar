package model;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

@Table(name = "register_courses")

public class RegisterCourse  extends BaseEntity<Long> {

    private double mark;
    private boolean isPassed;
    private Long studentId;
    private Long courseId;

    public RegisterCourse(Long aLong, double mark, boolean isPassed, Long studentId, Long courseId, Student student, Course course) {
        super(aLong);
        this.mark = mark;
        this.isPassed = isPassed;
        this.studentId = studentId;
        this.courseId = courseId;
        this.student = student;
        this.course = course;
    }

    public RegisterCourse(double mark, boolean isPassed, Long studentId, Long courseId, Student student, Course course) {
        this.mark = mark;
        this.isPassed = isPassed;
        this.studentId = studentId;
        this.courseId = courseId;
        this.student = student;
        this.course = course;
    }

    @ManyToOne
    @JoinColumn(name="student_id",insertable = false,updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id",insertable = false,updatable = false)
    private Course course;
}
