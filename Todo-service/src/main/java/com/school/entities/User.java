package com.school.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    //    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String token;
    //    @NotNull
    private Boolean enabled;
    @ManyToOne
    private Role role;
    //        @Transient
//    private List<Role> roles = new ArrayList();
    @Transient
    private String beautifyRoleName;

    public User() {

    }

    public User(String username, String email, String password, String firstName, String lastName, Boolean enabled, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.role = role;
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void getFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

//    public List<Role> getRoles() {
//        roles.clear();
//        roles.add(role);
//        return roles;
//    }

//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBeautifyRoleName() {
        if (role == null) {
            return beautifyRoleName;
        }
        if (role.getName() == RoleName.ROLE_ADMIN) {
            beautifyRoleName = "Admin";
        } else if (role.getName() == RoleName.ROLE_USER) {
            beautifyRoleName = "User";
        } else if (role.getName() == RoleName.ROLE_PROFESSOR) {
            beautifyRoleName = "Professor";
        } else if (role.getName() == RoleName.ROLE_PARENT) {
            beautifyRoleName = "Parent";
        } else {
            beautifyRoleName = "Student";
        }
        return beautifyRoleName;
    }

    public void setBeautifyRoleName(String beautifyRoleName) {
        this.beautifyRoleName = beautifyRoleName;
    }

}

