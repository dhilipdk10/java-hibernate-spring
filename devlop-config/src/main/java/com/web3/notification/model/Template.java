package com.web3.notification.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.web3.notification.enums.ChannelEnum;
import com.web3.notification.util.ChannelTypeAttributeConvertor;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "template")
public class Template {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "email_content")
  private String emailContent;

  @Column(name = "sms_content")
  private String smsContent;

  @Column(name = "push_notification_content")
  private String pushNotificationContent;

  @Column(name = "channels", columnDefinition = "text", length = 60)
  @Convert(converter = ChannelTypeAttributeConvertor.class)
  private List<ChannelEnum> channels;

  @Column(name = "web_push_content")
  private String webPushContent;

  @Column(name = "in_app_content")
  private String inAppContent;

  @ManyToOne
  @JoinColumn(name = "cat_id")
  private Category category;

  public Template() {
  }

  public Template(Long id, String name, String description, List<ChannelEnum> channels) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
    this.channels = channels;
  }

  public Long getId() {
    return id;
  }

  public Template setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Template setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Template setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getEmailContent() {
    return emailContent;
  }

  public Template setEmailContent(String emailContent) {
    this.emailContent = emailContent;
    return this;
  }

  public String getSmsContent() {
    return smsContent;
  }

  public Template setSmsContent(String smsContent) {
    this.smsContent = smsContent;
    return this;
  }

  public String getPushNotificationContent() {
    return pushNotificationContent;
  }

  public Template setPushNotificationContent(String pushNotificationContent) {
    this.pushNotificationContent = pushNotificationContent;
    return this;
  }

  public List<ChannelEnum> getChannels() {
    return channels;
  }

  public Template setChannels(List<ChannelEnum> channels) {
    this.channels = channels;
    return this;
  }

  public String getWebPushContent() {
    return webPushContent;
  }

  public Template setWebPushContent(String webPushContent) {
    this.webPushContent = webPushContent;
    return this;
  }

  public String getInAppContent() {
    return inAppContent;
  }

  public Template setInAppContent(String inAppContent) {
    this.inAppContent = inAppContent;
    return this;
  }

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

}
