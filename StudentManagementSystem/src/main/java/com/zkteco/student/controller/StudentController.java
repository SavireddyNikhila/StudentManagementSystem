package com.zkteco.student.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.student.dto.Result;
import com.zkteco.student.dto.StudentDto;
import com.zkteco.student.entity.Student;
import com.zkteco.student.exceptions.StudentNotFoundException;
import com.zkteco.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService stdService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public Result addStudent(@RequestBody StudentDto stdDto) {
		Student std = modelMapper.map(stdDto, Student.class);
		return stdService.addStudent(std);
	}

	@GetMapping("/{id}")
	public Result getStudent(@PathVariable("id") String id) throws StudentNotFoundException {
		return stdService.getStudent(id);
	}

	@GetMapping("/byBranch/{branch}")
	public Result getStudentByBranch(@PathVariable("branch") String branch) throws StudentNotFoundException {
		return stdService.getStudentByBranch(branch);
	}

	@GetMapping
	public Result getAllStudents() throws StudentNotFoundException {
		return stdService.getAllStudents();
	}

	@PutMapping("/{id}")
	public Result updateStudent(@PathVariable("id") String id, @RequestBody StudentDto stdDto) throws StudentNotFoundException {
		Student std = modelMapper.map(stdDto, Student.class);
		return stdService.updateStudent(id, std);
	}

	@DeleteMapping("/{id}")
	public Result deleteStudent(@PathVariable("id") String id) throws StudentNotFoundException {
		return stdService.deleteStudent(id);
	}
}
