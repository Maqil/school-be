package com.school.repository;

import com.school.entities.Professor;
import com.school.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    public List<Subject> findBySubjectNameContains(@Param(value = "mc") String mc);
}
