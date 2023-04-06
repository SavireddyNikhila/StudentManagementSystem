package com.zkteco.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zkteco.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

	List<Student> findAllByBranch(String branch);

	Student findByEmail(String email);

	Student findByMobile(String mobile);

}
