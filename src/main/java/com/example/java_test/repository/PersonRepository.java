package com.example.java_test.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.java_test.exception.PersonNotFoundException;
import com.example.java_test.model.Person;
import com.example.java_test.service.DatabaseProvider;

public class PersonRepository {
	private final SessionFactory sessionFactory = DatabaseProvider.getSessionFactory();

	public void delete(Person person) {
		Session session = sessionFactory.openSession();
		// ! WARNING: Use null checks
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.remove(person);
			transaction.commit();
		} catch (Exception error) {
			if (transaction != null) {
				transaction.rollback();
			}

			error.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteAll() {
		Session session = sessionFactory.openSession();
		// ! WARNING: Use null checks
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.createMutationQuery("delete from Person").executeUpdate();
			transaction.commit();
		} catch (Exception error) {
			if (transaction != null) {
				transaction.rollback();
			}

			error.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<Person> findAll() {
		Session session = sessionFactory.openSession();

		List<Person> persons = session.createQuery("from Person", Person.class).list();

		session.close();

		return persons;
	}

	public Person findByName(String name) throws PersonNotFoundException {
		Session session = sessionFactory.openSession();
		// ! WARNING: Use null checks
		Transaction transaction = null;
		Person person = null;

		try {
			transaction = session.beginTransaction();
			person = session.get(Person.class, name);
			transaction.commit();
		} catch (Exception error) {
			if (transaction != null) {
				transaction.rollback();
			}

			error.printStackTrace();
		} finally {
			session.close();
		}

		if (person == null) {
			throw new PersonNotFoundException();
		}

		return person;
	}

	public void save(Person person) {
		Session session = sessionFactory.openSession();
		// ! WARNING: Use null checks
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.persist(person);
			transaction.commit();
		} catch (Exception error) {
			if (transaction != null) {
				transaction.rollback();
			}

			error.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void update(Person person) {
		Session session = sessionFactory.openSession();
		// ! WARNING: Use null checks
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.merge(person);
			transaction.commit();
		} catch (Exception error) {
			if (transaction != null) {
				transaction.rollback();
			}

			error.printStackTrace();
		} finally {
			session.close();
		}
	}
}
