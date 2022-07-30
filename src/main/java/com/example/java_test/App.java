package com.example.java_test;

import com.example.java_test.service.DatabaseProvider;
import com.example.java_test.view.HomePage;

public class App {
	public static void main(String[] args) {
		DatabaseProvider.initialize();

		new HomePage().setVisible(true);
	}
}
