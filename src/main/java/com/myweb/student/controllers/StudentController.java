package com.myweb.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import com.myweb.student.models.Student;
import com.myweb.student.models.StudentDto;
import com.myweb.student.service.StudentRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students") // Ensure all routes are prefixed with /students
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping({ "", "/", "/index" })
	public String listStudents(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		return "students/index";
	}

	@GetMapping("/create")
	public String showCreateStudentPage(Model model) {
		StudentDto studentDto = new StudentDto(); // Create a new instance of StudentDto
		model.addAttribute("studentDto", studentDto); // Add StudentDto to the model
		return "students/CreateStudent"; // Return the view name for the create student page
	}

	@PostMapping("/create")
	public String createStudent(@Valid @ModelAttribute StudentDto studentDto, BindingResult result) {

		if (result.hasErrors()) {
			return "students/CreateStudent"; // Redirect back to the form in case of errors
		}

		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setAge(studentDto.getAge());
		student.setEmail(studentDto.getEmail());
		student.setPhoneNo(studentDto.getPhoneNo());
		student.setStudentClass(studentDto.getStudentClass());

		// Save the student into the repository
		studentRepository.save(student);

		return "redirect:/students/index"; // Redirect to the list of students after saving
	}

	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int id) {
		try {
			Student student = studentRepository.findById((long) id).orElse(null);
			if (student == null) {
				model.addAttribute("errorMessage", "Student not found");
				return "redirect:/students";
			}
			model.addAttribute("student", student);

			StudentDto studentDto = new StudentDto();
			studentDto.setFirstName(student.getFirstName());
			studentDto.setLastName(student.getLastName());
			studentDto.setAge(student.getAge());
			studentDto.setEmail(student.getEmail());
			studentDto.setPhoneNo(student.getPhoneNo());
			studentDto.setStudentClass(student.getStudentClass());

			model.addAttribute("studentDto", studentDto);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return "redirect:/students";
		}
		return "students/EditStudent";
	}

	@PostMapping("/edit")
	public String updateStudent(Model model, @RequestParam int id, @Valid @ModelAttribute StudentDto studentDto,
			BindingResult result) {
		try {
			Student student = studentRepository.findById((long) id).orElse(null);
			if (student == null) {
				model.addAttribute("errorMessage", "Student not found");
				return "redirect:/students";
			}
			model.addAttribute("student", student);

			if (result.hasErrors()) {
				return "students/EditStudent";
			}

			student.setFirstName(studentDto.getFirstName());
			student.setLastName(studentDto.getLastName());
			student.setAge(studentDto.getAge());
			student.setEmail(studentDto.getEmail());
			student.setPhoneNo(studentDto.getPhoneNo());
			student.setStudentClass(studentDto.getStudentClass());

			studentRepository.save(student);

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}

		return "redirect:/students";
	}

	@GetMapping("/delete")
	public String deleteStudent(@RequestParam int id) {
		try {
			Student student = studentRepository.findById((long) id).orElse(null);
			if (student == null) {
				System.out.println("Student not found with ID: " + id);
				return "redirect:/students";
			}

			// Delete the student
			studentRepository.delete(student);

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}

		return "redirect:/students";
	}

}
