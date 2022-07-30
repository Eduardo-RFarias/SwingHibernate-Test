package com.example.java_test.controller;

import java.util.List;

import javax.swing.DefaultListModel;

import com.example.java_test.model.Person;
import com.example.java_test.model.Person.PersonBuilder;
import com.example.java_test.repository.PersonRepository;
import com.example.java_test.view.HomePage;

public class HomePageController {
	private final HomePage view;
	private final PersonRepository repository = new PersonRepository();

	public HomePageController(HomePage view) {
		this.view = view;
	}

	public void buttonHandler(Object source) {
		// ! Always reset the label error to be invisible.
		view.getLabelError().setText("");
		view.getLabelError().setVisible(false);

		if (source == view.getButtonConfirm()) {
			newPerson();
		} else if (source == view.getButtonList()) {
			listPeople();
		}
	}

	private void listPeople() {
		List<Person> people = repository.findAll();

		if (people.isEmpty()) {
			view.getLabelError().setText("Nenhuma pessoa cadastrada");
			view.getLabelError().setVisible(true);
			return;
		}

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Person person : people) {
			listModel.addElement(person.toString());
		}

		view.getPersonList().setModel(listModel);
		view.getPersonList().setVisible(true);
	}

	private void newPerson() {
		PersonBuilder person = Person.builder();
		person.name(view.getTextFieldNome().getText());

		try {
			String ageString = view.getTextFieldIdade().getText();
			person.age(Integer.parseInt(ageString));
		} catch (NumberFormatException error) {
			view.getLabelError().setText("Idade inválida");
			view.getLabelError().setVisible(true);
			return;
		}

		repository.save(person.build());
	}
}
