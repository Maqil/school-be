package com.school.repository;

import com.school.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    public List<Enrollment> findByUsernameContains(@Param(value = "mc") String mc);
}
