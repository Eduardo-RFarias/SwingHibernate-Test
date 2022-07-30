package com.example.java_test.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.swing.ListModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.java_test.exception.PersonNotFoundException;
import com.example.java_test.model.Person;
import com.example.java_test.repository.PersonRepository;
import com.example.java_test.view.HomePage;

class HomePageTests {
	private final PersonRepository repository = new PersonRepository();

	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Test
	void addNewPersonUsingView() {
		HomePage homePage = new HomePage();
		assertEquals("Home Page", homePage.getTitle());

		homePage.getTextFieldNome().setText("Eduardo");
		homePage.getTextFieldIdade().setText("21");
		homePage.getButtonConfirm().doClick();

		assertEquals(1, repository.findAll().size());

		try {
			Person person = repository.findByName("Eduardo");

			assertEquals("Eduardo", person.getName());
			assertEquals(Integer.valueOf(21), person.getAge());
		} catch (PersonNotFoundException e) {
			fail();
		}

	}

	@Test
	void listPersonUsingView() {
		Person person = new Person("Eduardo", 21);
		repository.save(person);

		HomePage homePage = new HomePage();
		homePage.getButtonList().doClick();

		ListModel<String> list = homePage.getPersonList().getModel();

		assertEquals(1, list.getSize());
		assertEquals(person.toString(), list.getElementAt(0));
	}
}
