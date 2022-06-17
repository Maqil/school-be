package com.school;

import com.school.entities.*;
import com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;


@SpringBootApplication
public class TodoServiceApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    AssignmentRepository assignmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Role r1 = roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        Role r2 = roleRepository.save(new Role(RoleName.ROLE_USER));
        Role r3 = roleRepository.save(new Role(RoleName.ROLE_PROFESSOR));
        Role r4 = roleRepository.save(new Role(RoleName.ROLE_STUDENT));
        Role r5 = roleRepository.save(new Role(RoleName.ROLE_PARENT));


        List<Role> listRoles = new ArrayList<Role>();
        listRoles.add(r1);
        listRoles.add(r2);

        User user1 = userRepository.save(new User("John.Gonzales", "admin@gmail.com", "admin", "John", "Gonzales", true, r1));
        User user2 = userRepository.save(new User("Marbela.Popo", "user1@gmail.com", "user1", "user1", "user1", true, r2));
//        User user3 = userRepository.save(new User("user2", "use2r@gmail.com", "user2", "user2", "user2", true, r1));
//        User user4 = userRepository.save(new User("admin", "admin@gmail.com", "admin", "admin", "admin", true, new Role(2L)));
//        User user5 = userRepository.save(new User("admin", "admin@gmail.com", "admin", "admin", "admin", true, new Role(3L)));
//        User user6 = userRepository.save(new User("admin", "admin@gmail.com", "admin", "admin", "admin", true, new Role(4L)));


//        System.out.println(user2.getRole().getName());


       Admin admin = adminRepository.save(new Admin(null, "Dr1_Gr2", null, null, null, null));

       Enrollment s1 = enrollmentRepository.save(new Enrollment("Ziyad.Maqil", "Ziyad@gmail.com", "aziyad", true, "r4", "admin"));
      //  Enrollment s1 = enrollmentRepository.save(new Enrollment("Ziyad Maqil", "Ziyad@gmail.com", "aziyad", true, r4, admin, null));
      //  Enrollment s2 = studentRepository.save(new Enrollment(null, "Wert Jelly", "Jelly@gmail.com", "+212233445", null, admin));
      //  Enrollment s3 = studentRepository.save(new Enrollment(null, "bart Simpson", "simpson@gmail.com", "+212453445", null, admin));

       Grade g1 = gradeRepository.save(new Grade(null, "CP", null, admin));
       Grade g2 = gradeRepository.save(new Grade(null, "CE1", null, admin));
       Grade g3 = gradeRepository.save(new Grade(null, "CE2", null, admin));

       Subject sbj1 = subjectRepository.save(new Subject("Fr1", null, admin));
       Subject sbj2 = subjectRepository.save(new Subject("Math1", null, admin));
       Subject sbj3 = subjectRepository.save(new Subject("Bio1", null, admin));

       Set<Subject> listSbj = new HashSet<Subject>();
       listSbj.add(sbj1);
       listSbj.add(sbj2);
       listSbj.add(sbj3);

       Student stdGrd1 = studentRepository.save(new Student(null, s1, g1, null));
       Student stdGrd2 = studentRepository.save(new Student(null, s1, g1, null));
       Student stdGrd3 = studentRepository.save(new Student(null, s1, g3, null));

       Set<Student> listStdGrd = new HashSet<Student>();
       listStdGrd.add(stdGrd1);
       listStdGrd.add(stdGrd2);
       listStdGrd.add(stdGrd3);

       Professor p1 = professorRepository.save(new Professor("Johna.Smith",  "John@gmail.com", "Johnasmith", "john", "smith", true));
      //  Professor p2 = professorRepository.save(new Professor(null, "aicha curry", "curry@gmail.com", "+2125445", null, admin));
      //  Professor p3 = professorRepository.save(new Professor(null, "steve harr", "harr@gmail.com", "+2125445", null, admin));

       Todo t1 = todoRepository.save(new Todo(null, "Read 5 pages", p1, sbj1, null));
      //  Todo t2 = todoRepository.save(new Todo(null, "Read 6 pages", p2, sbj1, null));
       Todo t3 = todoRepository.save(new Todo(null, "Read 8 pages", p1, sbj3, null));
       Todo t4 = todoRepository.save(new Todo(null, "Read 5 pages", p1, sbj3, null));

       Set<Todo> listTodo = new HashSet<Todo>();
       listTodo.add(t1);
      //  listTodo.add(t2);
       listTodo.add(t3);
       listTodo.add(t4);

       Assignment assignment1 = assignmentRepository.save(new Assignment(null, stdGrd1, t1, true));
      //  Assignment assignment2 = assignmentRepository.save(new Assignment(null, stdGrd1, t2, true));
       Assignment assignment3 = assignmentRepository.save(new Assignment(null, stdGrd1, t3, true));
       Assignment assignment4 = assignmentRepository.save(new Assignment(null, stdGrd2, t4, true));
       Assignment assignment5 = assignmentRepository.save(new Assignment(null, stdGrd3, t4, false));

    }
}
