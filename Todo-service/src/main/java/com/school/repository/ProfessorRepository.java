package com.school.repository;

import com.school.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
//    public List<Professor> findByFullNameContains(@Param(value = "mc") String mc);
}
