package model;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RegisterCourse  extends BaseEntity<Long> {

    private double mark;
    private boolean isPassed;
    private Long studentId;
    private Long courseId;

    @ManyToOne
    @JoinColumn(name="student_id",insertable = false,updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id",insertable = false,updatable = false)
    private Course course;
}
