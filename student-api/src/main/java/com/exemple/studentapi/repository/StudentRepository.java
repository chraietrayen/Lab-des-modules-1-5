package com.exemple.studentapi.repository;
import com.exemple.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring Data JPA provides basic CRUD operations automatically!
}
