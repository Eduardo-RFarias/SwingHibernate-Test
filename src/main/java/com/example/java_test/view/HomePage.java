package com.example.java_test.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.example.java_test.controller.HomePageController;

public class HomePage extends JFrame implements ActionListener {
	private final JLabel labelNome;
	private final JLabel labelIdade;
	private final JTextField textFieldNome;
	private final JTextField textFieldIdade;
	private final JButton buttonConfirm;
	private final JButton buttonList;
	private final JLabel labelError;
	private final JList<String> personList;

	private final HomePageController controller;

	public HomePage() {
		super("Home Page");

		setSize(800, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		labelError = new JLabel();
		labelError.setVisible(false);
		add(labelError, BorderLayout.SOUTH);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout());
		add(formPanel, BorderLayout.NORTH);

		labelNome = new JLabel("Nome: ");
		formPanel.add(labelNome);

		textFieldNome = new JTextField(10);
		formPanel.add(textFieldNome);

		labelIdade = new JLabel("Idade: ");
		formPanel.add(labelIdade);

		textFieldIdade = new JTextField(10);
		formPanel.add(textFieldIdade);

		buttonConfirm = new JButton("Confirm");
		buttonConfirm.addActionListener(this);
		formPanel.add(buttonConfirm);

		buttonList = new JButton("List");
		buttonList.addActionListener(this);
		formPanel.add(buttonList);

		personList = new JList<>();
		personList.setVisible(false);
		add(personList, BorderLayout.CENTER);

		controller = new HomePageController(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		this.controller.buttonHandler(event.getSource());
	}

	public JButton getButtonConfirm() {
		return buttonConfirm;
	}

	public JButton getButtonList() {
		return buttonList;
	}

	public HomePageController getController() {
		return controller;
	}

	public JLabel getLabelError() {
		return labelError;
	}

	public JLabel getLabelIdade() {
		return labelIdade;
	}

	public JLabel getLabelNome() {
		return labelNome;
	}

	public JList<String> getPersonList() {
		return personList;
	}

	public JTextField getTextFieldIdade() {
		return textFieldIdade;
	}

	public JTextField getTextFieldNome() {
		return textFieldNome;
	}
}
