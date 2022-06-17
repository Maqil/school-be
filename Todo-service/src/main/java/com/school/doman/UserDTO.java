package com.school.doman;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.school.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserDTO {
    Long userID;
    String username;
    String email;
    String password;
    String firstName;
    String lastName;
    String phone;
    Role role;
}
