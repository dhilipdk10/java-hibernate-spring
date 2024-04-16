package com.web3.notification.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "cat_id", nullable = false)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "priority", columnDefinition = "enum('HIGH','MEDIUM','LOW')", nullable = false)
  private String priority;

  @Column(name = "ttl")
  private String ttl;

  @Column(name = "rate_limit")
  private String rateLimit;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
  private List<Template> templates;

  public Category() {
  }

  public Category(Long id, String name, String description, String priority, String ttl, String rateLimit) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
    this.priority = priority;
    this.ttl = ttl;
    this.rateLimit = rateLimit;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPriority() {
    return this.priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getTtl() {
    return this.ttl;
  }

  public void setTtl(String ttl) {
    this.ttl = ttl;
  }

  public String getRateLimit() {
    return this.rateLimit;
  }

  public void setRateLimit(String rateLimit) {
    this.rateLimit = rateLimit;
  }

  public List<Template> getTemplates() {
    return this.templates;
  }

  public void setTemplates(List<Template> templates) {
    this.templates = templates;
  }

}
