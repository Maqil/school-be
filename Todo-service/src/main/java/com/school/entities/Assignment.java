package com.school.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignementAiid;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Todo todo;

    private Boolean isDone = false;
}
