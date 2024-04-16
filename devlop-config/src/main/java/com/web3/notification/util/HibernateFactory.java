package com.web3.notification.util;

import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateFactory {
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
	if (null == sessionFactory) {
	  buildSessionFactory();
	}
	return sessionFactory;
  }

  public static SessionFactory buildSessionFactory() {
	Configuration configuration = new Configuration();

	configuration.setProperty("hibernate.hikari.connectionTimeout", System.getenv("HIKARI_CONNECTION_TIMEOUT"));
	configuration.setProperty("hibernate.hikari.minimumIdle", System.getenv("HIKARI_MINIMUM_IDLE"));
	configuration.setProperty("hibernate.hikari.maximumPoolSize", System.getenv("HIKARI_MAXIMUM_POOL_SIZE"));
	configuration.setProperty("hibernate.hikari.idleTimeout", System.getenv("HIKARI_IDLE_TIMEOUT"));
    
	configuration.setProperty(URL, System.getenv(Constant.DB_URL));
	configuration.setProperty(USER, System.getenv(Constant.DB_USERNAME));
	configuration.setProperty(PASS, System.getenv(Constant.DB_PASSWORD));
	configuration.setProperty(HBM2DDL_AUTO, System.getenv(Constant.DB_HBM2DDL_AUTO));

	configuration.configure("hibernate.cfg.xml");
	try {
	  sessionFactory = configuration.buildSessionFactory();
	} catch (HibernateException e) {
	  System.err.println("Initial SessionFactory creation failed." + e);
	  throw new ExceptionInInitializerError(e);
	}
	return sessionFactory;
  }
}