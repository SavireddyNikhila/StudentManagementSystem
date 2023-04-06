package com.zkteco.student.validations;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.zkteco.student.dto.Result;
import com.zkteco.student.entity.Student;
import com.zkteco.student.repository.StudentRepository;

public class ValidateStudent {

	@Autowired
	private StudentRepository stdRepo;
	
	public Result validate(Student std) {

		// validating student id
		if (Objects.nonNull(std.getId()) && !"".equals(std.getId())) {
			if (std.getId().length() <= 36) {
				Optional<Student> s = stdRepo.findById(std.getId());
				if (s.isPresent()) {
					return new Result("SMSE003", "Student already exists with the given id", "[]");
				}
			} else {
				return new Result("SMSE002", "Length of id should not exceed 36 characters", "[]");
			}
		} else {
			return new Result("SMSE001", "Id should not be null", "[]");
		}

		// validating firstname
		if (Objects.nonNull(std.getFirstName()) && !"".equals(std.getFirstName())) {
			if (!(std.getFirstName().length() <= 50)) {
				return new Result("SMSE005", "Length of firstname should not exceed 50 characters", "[]");
			}
		} else
			return new Result("SMSE004", "firstName should not be null", "[]");

		// validating lastname
		if (Objects.nonNull(std.getLastName()) && !"".equals(std.getLastName())) {
			if (!(std.getLastName().length() <= 50))
				return new Result("SMSE006", "Length of lastname should not exceed 50 characters", "[]");
		}

		// validating gender
		if (Objects.nonNull(std.getGender()) && !"".equals(std.getGender())) {
			if (std.getGender().length() == 1) {
				if (!(std.getGender().matches("(?:[M|F|O])"))) {
					return new Result("SMSE008", "Provide proper gender[M|F|O]", "[]");
				}
			} else {
				return new Result("SMSE007", "Length of gender should be 1", "[]");
			}
		}

		// validating email
		if (Objects.nonNull(std.getEmail()) && !"".equals(std.getEmail())) {
			if ((std.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				Student s = stdRepo.findByEmail(std.getEmail());
				if (s != null) {
					return new Result("SMSE011", "Student already exists with the given email", "[]");
				}
			} else {
				return new Result("SMSE010", "Email should match with proper format like (abc@gmail.com)", "[]");
			}

		} else {
			return new Result("SMSE009", "Email should not be null", "[]");
		}

		// validating phoneNumber
		if (Objects.nonNull(std.getMobile()) && !"".equals(std.getMobile())) {
			if ((std.getMobile().matches("[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				Student s = stdRepo.findByMobile(std.getMobile());
				if (s != null) {
					return new Result("SMSE014", "Student already exists with the given mobile", "[]");
				}
			} else
				return new Result("SMSE013",
						"PhoneNumber should match with proper format like (+[countrycode]-[10 digit mobile number]",
						"[]");
		} else {
			return new Result("SMSE012", "Phone Number should not be null", "[]");
		}
		
		return new Result();

	}
	
	public Result validUpdate(Student std) {
		
		// validating firstname
		if (Objects.nonNull(std.getFirstName()) && !"".equals(std.getFirstName())) {
			if (!(std.getFirstName().length() <= 50))
				return new Result("SMSE005", "Length of firstname should not exceed 50 characters", "[]");
		}
		
		// validating firstname
		if (Objects.nonNull(std.getLastName()) && !"".equals(std.getLastName())) {
			if (!(std.getLastName().length() <= 50))
				return new Result("SMSE006", "Length of lastname should not exceed 50 characters", "[]");
		}
		
		// validating gender
		if (Objects.nonNull(std.getGender()) && !"".equals(std.getGender())) {
			if (std.getGender().length() == 1) {
				if (!(std.getGender().matches("(?:[M|F|O])"))) {
					return new Result("SMSE008", "Provide proper gender[M|F|O]", "[]");
				}
			} else {
				return new Result("SMSE007", "Length of gender should be 1", "[]");
			}
		}
		
		// validating mobile
		if (Objects.nonNull(std.getMobile()) && !"".equals(std.getMobile())) {
			if ((std.getMobile().matches("[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				Student st = stdRepo.findByMobile(std.getMobile());
				if (st != null) {
					return new Result("SMSE014", "Student already exists with the given mobile", "[]");
				}
			} else
				return new Result("SMSE013",
						"PhoneNumber should match with proper format like (+[countrycode]-[10 digit mobile number]",
						"[]");
		}
		
		// validating email
		if (Objects.nonNull(std.getEmail()) && !"".equals(std.getEmail())) {
			if ((std.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				Student st = stdRepo.findByEmail(std.getEmail());
				if (st != null) {
					return new Result("SMSE011", "Student already exists with the given email", "[]");
				}
			} else {
				return new Result("SMSE010", "Email should match with proper format like (abc@gmail.com)", "[]");
			}
		}
		return new Result();
		
	}
}
