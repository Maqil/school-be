package com.school.graphql.service;

import com.school.entities.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryTest {

    @Test
    void allRoles() {
        Role role = new Role(1L);
        Long id = role.getId();
        assertEquals(1, id);
    }
}