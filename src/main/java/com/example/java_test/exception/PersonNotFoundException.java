package com.example.java_test.exception;

public class PersonNotFoundException extends Exception {
	public PersonNotFoundException() {
		super("Person not found");
	}
}
