package com.example.java_test.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseProvider {
	private static SessionFactory sessionFactory;

	private DatabaseProvider() {
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			initialize();
		}

		return sessionFactory;
	}

	public static void initialize() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
}
