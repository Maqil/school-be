package com.school.graphql.service;

import com.school.entities.*;
import com.school.repository.*;
import com.school.security.jwt.JwtTokenUtil;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Query {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepository;

    private EnrollmentRepository enrollmentRepository;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;
    private SubjectRepository subjectRepository;
    private TodoRepository todoRepository;
    private GradeRepository gradeRepository;

    private RoleRepository roleRepository;

    public Query(UserRepository userRepository, EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, ProfessorRepository professorRepository, SubjectRepository subjectRepository, TodoRepository todoRepository, GradeRepository gradeRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
        this.todoRepository = todoRepository;
        this.roleRepository = roleRepository;
    }

    @GraphQLQuery(name = "allRoles")
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @GraphQLQuery(name = "users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GraphQLQuery(name = "findByUsername")
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //    Professor
    @GraphQLQuery(name = "fetchAllProfessors")
    public List<Professor> fetchAllProfessors() {
        return professorRepository.findAll();
    }

    @GraphQLQuery(name = "fetchProfessorById")
    public Professor fetchProfessorById(@GraphQLArgument(name = "id") Integer id) {
        return professorRepository.findById(id).get();
    }

//    @GraphQLQuery(name = "fetchProfessorByName")
//    public List<Professor> fetchProfessorByName(@GraphQLArgument(name = "name") String name) {
//        return professorRepository.findByFullNameContains(name);
//    }

    //    Todo
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GraphQLQuery(name = "fetchAllTodos")
    public List<Todo> fetchAllTodos() {
        return todoRepository.findAll();
    }

    @GraphQLQuery(name = "fetchTodoById")
    public Todo fetchTodoById(@GraphQLArgument(name = "id") Integer id) {
        return todoRepository.findById(id).get();
    }

    //    Enrollment
    @GraphQLQuery(name = "fetchAllEnrollments")
    public List<Enrollment> fetchAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @GraphQLQuery(name = "fetchEnrollmentById")
    public Enrollment fetchEnrollmentById(@GraphQLArgument(name = "id") Long id) {
        return enrollmentRepository.findById(id).get();
    }

    @GraphQLQuery(name = "fetchEnrollmentByName")
    public List<Enrollment> fetchEnrollmentByName(@GraphQLArgument(name = "name") String name) {
        return enrollmentRepository.findByUsernameContains(name);
    }

    //    Grade
    @GraphQLQuery(name = "fetchAllGrades")
    public List<Grade> fetchAllGrades() {
        return gradeRepository.findAll();
    }

    @GraphQLQuery(name = "fetchGradeById")
    public Grade fetchGradeById(@GraphQLArgument(name = "id") Integer id) {
        return gradeRepository.findById(id).get();
    }

    //    StudentGrade
    @GraphQLQuery(name = "fetchAllStudentGrades")
    public List<Student> fetchAllStudentGrades() {
        return studentRepository.findAll();
    }

    @GraphQLQuery(name = "fetchStudentGradeById")
    public Student fetchStudentGradeById(@GraphQLArgument(name = "id") Integer id) {
        return studentRepository.findById(id).get();
    }

    //    Subject
    @GraphQLQuery(name = "fetchAllSubjects")
    public List<Subject> fetchAllSubjects() {
        return subjectRepository.findAll();
    }

    @GraphQLQuery(name = "fetchSubjectById")
    public Subject fetchSubjectById(@GraphQLArgument(name = "id") Integer id) {
        return subjectRepository.findById(id).get();
    }

    @GraphQLQuery(name = "fetchSubjectByName")
    public List<Subject> fetchSubjectByName(@GraphQLArgument(name = "name") String name) {
        return subjectRepository.findBySubjectNameContains(name);
    }

    @GraphQLQuery(name = "getTodosByStudentId")
    public List<Subject> getTodosByStudentId(@GraphQLArgument(name = "id") Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id).get();
        logger.info("enrollment {}", enrollment);
        return null;
        // return subjectRepository.findBySubjectNameContains(name);
    }
}
