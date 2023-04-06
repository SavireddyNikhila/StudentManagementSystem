package com.zkteco.student.exceptions;

public class StudentNotFoundException extends Exception{

	public StudentNotFoundException() {
		super();
	}
	
	public StudentNotFoundException(String msg) {
		super(msg);
	}
}
