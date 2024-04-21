package model;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity<Long> {


    private String name;
    private Integer units;
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

}