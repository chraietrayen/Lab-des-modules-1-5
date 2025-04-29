package com.exemple.studentportal.service;

import com.exemple.studentportal.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    // In-memory database using a Map
    private final Map<Long, Student> studentDb = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public StudentServiceImpl() {
        // Add some sample data
        Student student1 = new Student(idCounter.incrementAndGet(), "John Doe", 21,
                "john@example.com", "Computer Science", "CS123456");
        Student student2 = new Student(idCounter.incrementAndGet(), "Jane Smith", 22,
                "jane@example.com", "Mathematics", "MT789012");
        Student student3 = new Student(idCounter.incrementAndGet(), "Bob Johnson", 20,
                "bob@example.com", "Physics", "PH345678");

        studentDb.put(student1.getId(), student1);
        studentDb.put(student2.getId(), student2);
        studentDb.put(student3.getId(), student3);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentDb.values());
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return Optional.ofNullable(studentDb.get(id));
    }

    @Override
    public Student saveStudent(Student student) {
        if (student.getId() == null) {
            // New student
            student.setId(idCounter.incrementAndGet());
        }
        // Ensure student ID is uppercase
        student.setStudentId(student.getStudentId().toUpperCase());
        studentDb.put(student.getId(), student);
        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        studentDb.remove(id);
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllStudents();
        }
        return studentDb.values().stream()
                .filter(student -> student.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        student.getCourse().toLowerCase().contains(keyword.toLowerCase()) ||
                        student.getStudentId().toUpperCase().contains(keyword.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getAllStudentsSorted(String sortBy, String direction) {
        Comparator<Student> comparator = switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(Student::getName);
            case "age" -> Comparator.comparing(Student::getAge);
            case "email" -> Comparator.comparing(Student::getEmail);
            case "course" -> Comparator.comparing(Student::getCourse);
            case "studentid" -> Comparator.comparing(Student::getStudentId);
            default -> Comparator.comparing(Student::getId);
        };

        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }

        return studentDb.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getAllStudentsPaginated(int page, int size, String sortBy, String direction) {
        List<Student> sortedStudents = getAllStudentsSorted(sortBy, direction);
        int totalItems = sortedStudents.size();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        // Validate page number
        page = Math.max(0, Math.min(page, totalPages - 1));

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalItems);

        List<Student> pageContent = sortedStudents.subList(fromIndex, toIndex);

        Map<String, Object> response = new HashMap<>();
        response.put("students", pageContent);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);
        response.put("totalItems", totalItems);

        return response;
    }

    @Override
    public Map<String, Object> searchStudentsPaginated(String keyword, int page, int size, String sortBy, String direction) {
        List<Student> filteredStudents = searchStudents(keyword);
        List<Student> sortedStudents = filteredStudents.stream()
                .sorted(getComparator(sortBy, direction))
                .collect(Collectors.toList());

        int totalItems = sortedStudents.size();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        page = Math.max(0, Math.min(page, totalPages - 1));

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalItems);

        Map<String, Object> response = new HashMap<>();
        response.put("students", sortedStudents.subList(fromIndex, toIndex));
        response.put("currentPage", page);
        response.put("totalPages", totalPages);
        response.put("totalItems", totalItems);
        response.put("keyword", keyword);

        return response;
    }

    private Comparator<Student> getComparator(String sortBy, String direction) {
        Comparator<Student> comparator = switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(Student::getName);
            case "age" -> Comparator.comparing(Student::getAge);
            case "email" -> Comparator.comparing(Student::getEmail);
            case "course" -> Comparator.comparing(Student::getCourse);
            case "studentid" -> Comparator.comparing(Student::getStudentId);
            default -> Comparator.comparing(Student::getId);
        };
        return "desc".equalsIgnoreCase(direction) ? comparator.reversed() : comparator;
    }
}