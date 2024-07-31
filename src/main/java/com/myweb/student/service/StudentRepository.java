package com.myweb.student.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myweb.student.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	

	
//  @GetMapping("/students/create")
//  public String showCreateForm(Model model) {
//      model.addAttribute("student", new Student());
//      return "create";
//  }
	
	
//	@GetMapping("/edit")
//	public String showEditForm(@RequestParam("id") Long id, Model model) {
//		model.addAttribute("student", studentService.getStudentById(id));
//		return "edit";
//	}
//
//	@PostMapping("/update")
//	public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
//		if (result.hasErrors()) {
//			return "edit";
//		}
//		studentService.saveStudent(student);
//		return "redirect:/students";
//	}
//
//	@GetMapping("/delete")
//	public String deleteStudent(@RequestParam("id") Long id) {
//		studentService.deleteStudentById(id);
//		return "redirect:/students";
//	}
	
	

}