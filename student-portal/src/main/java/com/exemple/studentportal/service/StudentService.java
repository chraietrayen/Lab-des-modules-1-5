package com.exemple.studentportal.service;

import com.exemple.studentportal.model.Student;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);

    List<Student> searchStudents(String keyword);
    List<Student> getAllStudentsSorted(String sortBy, String direction);
    Map<String, Object> getAllStudentsPaginated(int page, int size, String sortBy, String direction);
    Map<String, Object> searchStudentsPaginated(String keyword, int page, int size, String sortBy, String direction);
}