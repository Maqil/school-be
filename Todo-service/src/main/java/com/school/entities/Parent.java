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
@ToString
public class Parent extends User {

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Set<Student> students;

    @ManyToOne
    private Admin admin;

    public Parent() {}

    public Parent(String username, String email, String password, String firstName, String lastName, String phone, Boolean enabled) {
      super(username, email, password, firstName, lastName, phone, enabled, new Role(5L));
  }

    public Parent(String username, String email, String password) {
        super(username, email, password, new Role(5L));
        System.out.println(email);
    }
}
