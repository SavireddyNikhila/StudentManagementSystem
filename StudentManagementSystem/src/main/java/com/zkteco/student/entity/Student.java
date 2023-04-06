package com.zkteco.student.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_tbl")
public class Student {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "student_id", length = 36, unique = true)
	private String id;
	@Column(name = "first_name", length = 50)
	private String firstName;
	@Column(name = "last_name", length = 50)
	private String lastName;
	@Column(name = "gender", length = 1)
	private String gender;
	@Column(name = "branch", length = 30, nullable = false)
	private String branch;
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	@Column(name = "mobile", length = 20, unique = true)
	private String mobile;
	@Column(name = "email", length = 30, unique = true)
	private String email;
	@Column(name = "address", length = 255)
	private String address;
	@Column(name = "profile", length = 100)
	private String profile;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

}
