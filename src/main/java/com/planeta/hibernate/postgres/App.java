package com.planeta.hibernate.postgres;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.planeta.hibernate.postgres.model.User;

/**
 * Simple standalone JPA app.
 * Will load the user inserted by the script import-users.sql
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
public class App {
	
	private static Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		User employee = new User();
		employee.setName("stefan");
		User employe = new User();
		employe.setName("admin");
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.persist(employe);
		entityManager.getTransaction().commit();
		//User found = entityManager.find(User.class, 1L);
		Query query = entityManager.createQuery("SELECT e FROM User e");
		Collection<User> users = (Collection<User>) query.getResultList();
		for(User user: users){
			log.info("found=" + user);
		}
		//log.info("found=" + found);
	}
}
