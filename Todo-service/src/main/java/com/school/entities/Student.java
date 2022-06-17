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
public class Student extends User{
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Integer studentAiid;

    @ManyToOne
    private Grade grade;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Parent parent;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Set<Assignment> assignments;

    public Student(String username, String email, String password, String firstName, String lastName, String phone, Boolean enabled) {
      super(username, email, password, firstName, lastName, phone, enabled, new Role(4L));
    }

    public Student(String username, String email, String password) {
        super(username, email, password, new Role(4L));
    }
}
