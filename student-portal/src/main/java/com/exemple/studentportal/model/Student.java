package com.exemple.studentportal.model;

import jakarta.validation.constraints.*;

public class Student {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between {min} and {max} characters")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can only contain letters and spaces")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 16, message = "Student must be at least {value} years old")
    @Max(value = 99, message = "Age must be less than {value}")
    private Integer age;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Course is required")
    private String course;

    @NotBlank(message = "Student ID is required")
    @Pattern(regexp = "^[A-Z0-9]{8}$",
            message = "Student ID must be exactly 8 uppercase alphanumeric characters")
    private String studentId;

    // Constructors
    public Student() {
    }

    public Student(Long id, String name, Integer age, String email, String course, String studentId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
        this.studentId = studentId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId.toUpperCase(); // Ensure uppercase
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", course='" + course + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }

    // Additional utility methods
    public boolean isNew() {
        return this.id == null;
    }

    public String getFormattedInfo() {
        return String.format("%s (%s) - %s", name, studentId, course);
    }
}