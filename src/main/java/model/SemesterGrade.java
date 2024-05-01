package model;

import base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "semester_grade")
public class SemesterGrade extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private Double semester;

    @Column(name = "final_score")
    private Double finalScore;

    @Override
    public String toString() {
        return "SemesterGrade{" +
                "student=" + student +
                ", semester=" + semester +
                ", finalScore=" + finalScore +
                ", id=" + id +
                '}';
    }
}
