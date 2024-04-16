package com.web3.notification.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.web3.notification.enums.ChannelEnum;
import com.web3.notification.model.Template;
import com.web3.notification.util.HibernateFactory;
import com.web3.notification.util.Utility;

public class TemplateService {

	public String createNewTemplate(Template template) {
		Transaction transaction = null;
		Session session = HibernateFactory.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.save(template);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return "Template Created Successfully";
	}

	public List<Template> getAllTemplate() {
		List<Template> result = new ArrayList<>();
		try (Session session = HibernateFactory.getSessionFactory().openSession();) {
			List<?> queryResults = session.createNativeQuery("select id, name, description, channels from template")
					.list();
			for (Iterator<?> it = queryResults.iterator(); it.hasNext();) {
				Object[] myResult = (Object[]) it.next();
				Long id = (myResult[0] != null ? ((BigInteger) myResult[0]).longValue() : null);
				String name = (myResult[1] != null ? (String) myResult[1] : null);
				String description = (myResult[2] != null ? (String) myResult[2] : null);
				String channelsString = (myResult[3] != null ? (String) myResult[3] : "[]");
				List<ChannelEnum> channels = Utility.fromJson(channelsString, new TypeReference<List<ChannelEnum>>() {
				});
				result.add(new Template(id, name, description, channels));
			}
		}
		return result;
	}

	public Template getTemplateDetailsById(long id) {
		try (Session session = HibernateFactory.getSessionFactory().openSession();) {
			Template template = session.get(Template.class, id);
			return template;
		}
	}

	public String updateAnExistingTemplate(long id, Template template) {
		Transaction transaction = null;
		try (Session session = HibernateFactory.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			Template existingTemplate = session.find(Template.class, id);
			existingTemplate.setName(template.getName()).setChannels(template.getChannels())
					.setDescription(template.getDescription()).setEmailContent(template.getEmailContent())
					.setPushNotificationContent(template.getPushNotificationContent())
					.setSmsContent(template.getSmsContent()).setInAppContent(template.getInAppContent());

			session.save(existingTemplate);
			session.getTransaction().commit();
			return "update";
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			throw ex;
		}

	}

	public String deleteTemplateDetailsById(long id) {
		Session session = HibernateFactory.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Template template = session.find(Template.class, id);
			session.delete(template);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			session.close();
		}

		return "deleted";
	}

}
