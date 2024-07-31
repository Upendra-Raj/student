package com.myweb.student.models;


import jakarta.validation.constraints.*;

public class StudentDto {

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be a positive number")
    @Max(value = 120, message = "Age must be less than 120")
    private Integer age;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be valid")
    private String phoneNo;

    @NotEmpty(message = "Class is required")
    private String studentClass;

    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
