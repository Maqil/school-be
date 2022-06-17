package com.school.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
public class Enrollment extends User {

    private Long enrollmentAiid;

    @OneToMany(mappedBy = "enrollment", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Set<Student> registrations;

    @ManyToOne
    private Admin admin;

    public Enrollment() {

    }

    public Enrollment(String username, String email, String password, Boolean enabled, String firstName, String lastName) {
        super(username, email, password, firstName, lastName, enabled, new Role(4L));
    }
}
