package com.school.repository;

import com.school.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, String> {
//    public List<Professor> findByFullNameContains(@Param(value = "mc") String mc);
}
