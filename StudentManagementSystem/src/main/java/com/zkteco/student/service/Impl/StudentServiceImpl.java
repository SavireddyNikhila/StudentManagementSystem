package com.zkteco.student.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkteco.student.dto.Result;
import com.zkteco.student.dto.StudentDto;
import com.zkteco.student.entity.Student;
import com.zkteco.student.exceptions.StudentNotFoundException;
import com.zkteco.student.repository.StudentRepository;
import com.zkteco.student.service.StudentService;
import com.zkteco.student.validations.ValidateStudent;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository stdRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ValidateStudent validateStd;

	@Override
	public Result addStudent(Student std) {
		Result res = validateStd.validate(std);
		if (res == null) {
			std.setCreatedDate(LocalDateTime.now());
			std.setUpdatedDate(LocalDateTime.now());
			stdRepo.save(std);
			StudentDto stdDto = modelMapper.map(std, StudentDto.class);
			return new Result("SMSI001", "Student added Successully", stdDto);
		}
		return res;
	}

	@Override
	public Result getStudent(String id) throws StudentNotFoundException {
		Student std = stdRepo.findById(id).get();
		if (std == null) {
			throw new StudentNotFoundException("Failed to fetch! No student found with the given id: " + id);
		}
		StudentDto stdDto = modelMapper.map(std, StudentDto.class);
		return new Result("SMSI003", "Student fetched successfully", stdDto);
	}

	@Override
	public Result getStudentByBranch(String branch) throws StudentNotFoundException {
		List<Student> students = stdRepo.findAllByBranch(branch);
		if (students == null) {
			throw new StudentNotFoundException("Failed to fetch! No student found with the given branch" + branch);
		}
		List<StudentDto> studentsDto = (List<StudentDto>) modelMapper.map(students, StudentDto.class);
		return new Result("SMSI004", "Students fetched successfully", studentsDto);
	}

	@Override
	public Result getAllStudents() throws StudentNotFoundException {
		List<Student> students = stdRepo.findAll();
		if (students == null) {
			throw new StudentNotFoundException("Failed to fetch! No student found");
		}
		List<StudentDto> studentsDto = (List<StudentDto>) modelMapper.map(students, StudentDto.class);
		return new Result("SMSI005", "Students fetched successfully", studentsDto);
	}

	@Override
	public Result updateStudent(String id, Student std) throws StudentNotFoundException {
		Student s = stdRepo.findById(id).get();
		if (s == null) {
			throw new StudentNotFoundException("Failed to update! No student found with the given id: " + id);
		} else {
			Result res = validateStd.validUpdate(std);
			if(res != null) {
				return res;
			}
			else {
			if (Objects.nonNull(std.getFirstName()) && !"".equals(std.getFirstName()))
				s.setFirstName(std.getFirstName());
			if (Objects.nonNull(std.getLastName()) && !"".equals(std.getLastName()))
				s.setLastName(s.getLastName());
			if (Objects.nonNull(s.getGender()) && !"".equals(s.getGender()))
				s.setGender(s.getGender());
			if (Objects.nonNull(s.getBranch()) && !"".equals(s.getBranch()))
				s.setBranch(s.getBranch());
			if (Objects.nonNull(s.getDateOfBirth()))
				s.setDateOfBirth(s.getDateOfBirth());
			if (Objects.nonNull(s.getMobile()) && !"".equals(s.getMobile()))
				s.setMobile(s.getMobile());
			if (Objects.nonNull(s.getEmail()) && !"".equals(s.getEmail()))
				s.setEmail(s.getEmail());
			if (Objects.nonNull(std.getAddress()) && !"".equals(std.getAddress()))
				s.setAddress(std.getAddress());
			if (Objects.nonNull(s.getProfile()) && !"".equals(s.getProfile()))
				s.setProfile(s.getProfile());
			
			s.setUpdatedDate(LocalDateTime.now());
		}
		}
		
		stdRepo.save(s);
		StudentDto stdDto = modelMapper.map(s, StudentDto.class);
		return new Result("SMSI006","Updated successfully",stdDto);
	}

	@Override
	public Result deleteStudent(String id) throws StudentNotFoundException {
		Student s = stdRepo.findById(id).get();
		if (s == null) {
			throw new StudentNotFoundException("Failed to delete! No student found with the given id: " + id);
		}
		stdRepo.deleteById(id);
		StudentDto stdDto = modelMapper.map(s, StudentDto.class);
		return new Result("SMSI007", "Student deleted successfully", stdDto);
	}

}
