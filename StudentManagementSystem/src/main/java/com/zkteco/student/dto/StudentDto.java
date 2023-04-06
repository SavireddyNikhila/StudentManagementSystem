package com.zkteco.student.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private String id;
	private String firstName;
	private String lastName;
	private String gender;
	private String branch;
	private Date dateOfBirth;
	private String mobile;
	private String email;
	private String address;
	private String profile;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
