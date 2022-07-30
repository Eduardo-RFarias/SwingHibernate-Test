package com.example.java_test.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.java_test.exception.PersonNotFoundException;
import com.example.java_test.model.Person;
import com.example.java_test.repository.PersonRepository;

class PersonModelTests {
	private final PersonRepository repository = new PersonRepository();

	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Test
	void testPersonSave() {
		Person person = new Person("Eduardo", 21);

		assertEquals("Eduardo", person.getName());
		assertEquals(Integer.valueOf(21), person.getAge());

		repository.save(person);

		try {
			Person savedPerson = repository.findByName("Eduardo");

			assertEquals("Eduardo", savedPerson.getName());
			assertEquals(Integer.valueOf(21), savedPerson.getAge());
		} catch (PersonNotFoundException error) {
			fail();
		}
	}

	@Test
	void testPersonList() {
		Person person = new Person("Eduardo", 21);
		Person person2 = new Person("Adriano", 17);

		repository.save(person);
		repository.save(person2);

		List<Person> persons = repository.findAll();
		assertEquals(2, persons.size());

		Person savedperson = persons.get(0);

		assertEquals("Eduardo", savedperson.getName());
		assertEquals(Integer.valueOf(21), savedperson.getAge());

		Person savedperson2 = persons.get(1);

		assertEquals("Adriano", savedperson2.getName());
		assertEquals(Integer.valueOf(17), savedperson2.getAge());
	}

	@Test
	void testPersonUpdate() {
		Person person = new Person("Eduardo", 21);

		repository.save(person);

		person.setAge(22);

		repository.update(person);

		try {
			Person updatedPerson = repository.findByName("Eduardo");

			assertEquals(person.getName(), updatedPerson.getName());
			assertEquals(person.getAge(), updatedPerson.getAge());
		} catch (PersonNotFoundException error) {
			fail();
		}
	}

	@Test
	void testPersonDelete() {
		Person person = new Person("Eduardo", 21);
		repository.save(person);

		try {
			repository.findByName("Eduardo");
		} catch (PersonNotFoundException error) {
			fail();
		}

		repository.delete(person);

		assertThrowsExactly(PersonNotFoundException.class, () -> {
			repository.findByName("Eduardo");
		});
	}
}
