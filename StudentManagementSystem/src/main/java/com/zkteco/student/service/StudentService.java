package com.zkteco.student.service;

import com.zkteco.student.dto.Result;
import com.zkteco.student.entity.Student;
import com.zkteco.student.exceptions.StudentNotFoundException;

public interface StudentService {

	public Result addStudent(Student std);

	public Result getStudent(String id) throws StudentNotFoundException;

	public Result getStudentByBranch(String branch) throws StudentNotFoundException;

	public Result getAllStudents() throws StudentNotFoundException;

	public Result updateStudent(String id, Student std) throws StudentNotFoundException;

	public Result deleteStudent(String id) throws StudentNotFoundException;

}
