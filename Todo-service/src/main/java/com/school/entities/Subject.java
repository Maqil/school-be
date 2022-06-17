package com.school.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subjectAiid;

    private String subjectName;

//    @ManyToMany
//    private Set<StudentGrade> stdGrades;

//    @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
//    private Set<Professor> professors;

//    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
//    private Set<Enrollment> enrollments;

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.ALL})
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Set<Todo> todos;

    @ManyToOne
    private Admin admin;

    public Subject (String subjectName, Set todos, Admin admin) {
      this.subjectName = subjectName;
      this.todos = todos;
      this.admin = admin;
    }
}
