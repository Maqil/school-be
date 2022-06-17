package com.school.graphql.service;

import com.school.entities.*;
import com.school.repository.*;
import com.school.security.jwt.JwtTokenUtil;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:3000")
public class Mutation {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private AdminRepository adminRepository;
    private EnrollmentRepository enrollmentRepository;
    private ProfessorRepository professorRepository;
    private SubjectRepository subjectRepository;
    private TodoRepository todoRepository;
    private GradeRepository gradeRepository;

    public Mutation(AdminRepository adminRepository, EnrollmentRepository enrollmentRepository, ProfessorRepository professorRepository, SubjectRepository subjectRepository, TodoRepository todoRepository, GradeRepository gradeRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.professorRepository = professorRepository;
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
        this.todoRepository = todoRepository;
        this.adminRepository = adminRepository;
    }

    @GraphQLMutation(name = "addAdmin")
//    public Admin addAdmin(@GraphQLArgument(name = "username") String username, @GraphQLArgument(name = "admin") Admin admin) {
    public Admin addAdmin(@GraphQLNonNull @GraphQLArgument(name = "username") String username,
                          @GraphQLNonNull @GraphQLArgument(name = "email") String email,
                          @GraphQLNonNull @GraphQLArgument(name = "password") String password) {
//        return adminRepository.save(new Admin(admin.getUsername(), admin.getEmail(), admin.getPassword(), admin.getFirstName(), admin.getLastName(), admin.getEnabled()));
        return adminRepository.save(new Admin(username, email, password));
    }

    // @CrossOrigin(origins = "http://localhost:3000")
    // @PreAuthorize("hasRole('ADMIN')")
    // @GraphQLMutation(name = "addEnrollment")
    // public Enrollment addEnrollment(@GraphQLArgument(name = "enrollment") Enrollment enrollment) {
    //     System.out.println(enrollment.getEmail());
    //     return enrollmentRepository.save(new Enrollment(enrollment.getUsername(), enrollment.getEmail(), enrollment.getPassword(), enrollment.getFirstName(), enrollment.getLastName(), enrollment.getEnabled()));
    // }

    @PreAuthorize("hasRole('ADMIN')")
    @GraphQLMutation(name = "addProfessor")
//    public Professor addProfessor(@GraphQLArgument(name = "professor") Professor professor) {
    public Professor addProfessor(@GraphQLNonNull @GraphQLArgument(name = "username") String username,
                                  @GraphQLNonNull @GraphQLArgument(name = "email") String email,
                                  @GraphQLNonNull @GraphQLArgument(name = "password") String password) {
        return professorRepository.save(new Professor(username, email, password));
//        return professorRepository.save(new Professor(professor.getUsername(), professor.getEmail(), professor.getPassword(), professor.getFirstName(), professor.getLastName(), professor.getEnabled()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GraphQLMutation(name = "addGrade")
    public Grade addGrade(@GraphQLArgument(name = "grade") Grade grade) {
        return gradeRepository.save(grade);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GraphQLMutation(name = "addSubject")
    public Subject addSubject(@GraphQLArgument(name = "subject") Subject subject) {
        return subjectRepository.save(subject);
    }

    //    @PreAuthorize("hasRole('PROFESSOR')")
    @GraphQLMutation(name = "addTodo")
    public Todo addTodo(@GraphQLArgument(name = "todo") Todo todo) {
        return todoRepository.save(todo);
    }
}
