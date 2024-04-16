package com.web3.notification.service;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.web3.notification.dao.CategoryDao;
import com.web3.notification.exception.BadRequestException;
import com.web3.notification.exception.NotFoundException;
import com.web3.notification.model.Category;

public class CategoryService {

  CategoryDao categoryDao = new CategoryDao();

  public String createNewCategory(Category category) {
    if (Strings.isNullOrEmpty(category.getName())) {
      throw new BadRequestException("Name is mandatory field");
    }
    if (Strings.isNullOrEmpty(category.getPriority())) {
      throw new BadRequestException("Priority is mandatory field");
    }
    if (!((category.getPriority().equalsIgnoreCase("high")) || (category.getPriority().equalsIgnoreCase("low")) || (category.getPriority().equalsIgnoreCase("medium"))))
      throw new BadRequestException("Invalid Entry in Priority");
    return categoryDao.createNewCategory(category);
  }

  public List<Category> getAllCategory() {
    return categoryDao.getAllCategory();
  }

  public Category getCategoryDetailsById(Map<String, String> pathParaMeter) {
    Category category = categoryDao.getCategoryDetailsById(Long.parseLong(pathParaMeter.get("id")));
    if(category == null) {
      throw new NotFoundException("Category not found with requested id :"+Long.parseLong(pathParaMeter.get("id")));
    }
    return category;
  }

  public String updateAnExistingCategory(Map<String, String> map, Category category) {
    return categoryDao.updateAnExistingCategory(Long.parseLong(map.get("id")), category);
  }

  public String deleteCategoryDetailsById(Map<String, String> map) {
    return categoryDao.deleteCategoryDetailsById(map);
  }

}
