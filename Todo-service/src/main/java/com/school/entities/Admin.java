package com.school.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Entity
@AllArgsConstructor
@Builder
@ToString
public class Admin extends User {

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Professor> professorSet;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Grade> gradeSet;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Subject> subjectSet;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Student> studentSet;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Parent> parentSet;

    public Admin() {
    }

    public Admin(String username, String email, String password, String firstName, String lastName, String phone,Boolean enabled) {
        super(username, email, password, firstName, lastName, phone, enabled, new Role(1L));
    }

    public Admin(String username, String email, String password) {
        super(username, email, password, new Role(1L));
    }
}
