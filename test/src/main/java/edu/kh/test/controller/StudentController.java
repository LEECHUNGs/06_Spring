package edu.kh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.test.model.dto.Student;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {
	
	@PostMapping("select")
	public String selectStudent(HttpServletRequest req, @ModelAttribute Student student) {
	
		
		System.out.println(student);
		
		req.setAttribute("stdName", student.getStdName());
		req.setAttribute("stdAge", student.getStdAge());
		req.setAttribute("stdAddress", student.getStdAddress());
		
		
		return "student/select";
		
	}
}
