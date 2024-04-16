package com.web3.notification.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.web3.notification.model.Category;
import com.web3.notification.util.HibernateFactory;

public class CategoryDao {
  
  public String createNewCategory(Category category) {
    Transaction transaction = null;
    Session session = HibernateFactory.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      session.save(category);
      transaction.commit();
    } catch (Exception ex) {
      if (transaction != null)
        transaction.rollback();
      throw ex;
    } finally {
      if (session != null)
        session.close();
    }
    return "Created Successfully";
  }
  
  public Category getCategoryDetailsById(long id) {
    try (Session session = HibernateFactory.getSessionFactory().openSession();) {
      Category category = session.get(Category.class, id);
      return category;
    }
  }
  
  public String deleteCategoryDetailsById(Map<String, String> map) {
    Session session = HibernateFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try {
      Category category = session.find(Category.class, map);
      session.delete(category);
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
  public List<Category> getAllCategory() {
    List<Category> result = new ArrayList<>();
    try (Session session = HibernateFactory.getSessionFactory().openSession();) {
      List<?> queryResults = session.createNativeQuery("select cat_id, name, description, priority, ttl, rate_limit from category").list();
      for (Iterator<?> it = queryResults.iterator(); it.hasNext();) {
        Object[] myResult = (Object[]) it.next();
        Long id = (myResult[0] != null ? ((Number) myResult[0]).longValue() : null);
        String name = (myResult[1] != null ? (String) myResult[1] : null);
        String description = (myResult[2] != null ? (String) myResult[2] : null);
        String priority = (myResult[3] != null ? (String) myResult[3] :null);
        String ttl = (myResult[4] != null ? (String) myResult[4] :null);
        String rateLimit = (myResult[5] != null ? (String) myResult[5] :null);
        result.add(new Category(id, name, description, priority, ttl, rateLimit));
      }
    }
    return result;
  }
  
  public String updateAnExistingCategory(long parseLong, Category category) {
    Transaction transaction = null;
    try (Session session = HibernateFactory.getSessionFactory().openSession();) {
      transaction = session.beginTransaction();
      Category existingCategory = session.get(Category.class, parseLong);
      existingCategory.setName(category.getName());
      existingCategory.setDescription(category.getDescription());
      existingCategory.setPriority(category.getPriority());
      existingCategory.setRateLimit(category.getRateLimit());
      existingCategory.setTtl(category.getTtl());
      session.save(existingCategory);
      session.getTransaction().commit();
      return "Update Successfully";
    } catch (Exception ex) {
      if (transaction != null)
        transaction.rollback();
      throw ex;
    }

  }

  }
