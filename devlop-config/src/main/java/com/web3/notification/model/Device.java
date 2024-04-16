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
import com.web3.notification.model.Device_Tag;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "device")
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "device_id", nullable = false)
  private int id;
  
  @Column(name = "device_type", nullable = false)
  private String device_type;
  
  @Column(name = "identifier", columnDefinition = "enum('ANDRIOD', 'IOS', 'WEB_PUSH', 'EMAIL', 'SMS')", nullable = false)
  private String identifier;
  
  @Column(name = "external_user_id")
  private String external_user_id;
  
  @Column(name = "app_version")
  private String app_version;
  
  @Column(name = "device_mode")
  private String device_mode;
  
  @Column(name = "device_os")
  private String device_os;
  
  @Column(name = "created_at")
  private String created_at;
  
  @Column(name = "latitude")
  private float latitude;
  
  @Column(name = "longitude")
  private float longitude;
  
  @Column(name = "country")
  private Long country;
  
  @Column(name = "timezone")
  private String timezone;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "device", cascade = CascadeType.ALL)
  private List<Device_Tag> tags;

  public Device () {
    
  }
  public Device(int id, String device_type, String identifier, String external_user_id, String app_version,
      String device_mode, String device_os, String created_at, float latitude, float longitude, Long country,
      String timezone, List<Device_Tag> tags) {
    super();
    this.id = id;
    this.device_type = device_type;
    this.identifier = identifier;
    this.external_user_id = external_user_id;
    this.app_version = app_version;
    this.device_mode = device_mode;
    this.device_os = device_os;
    this.created_at = created_at;
    this.latitude = latitude;
    this.longitude = longitude;
    this.country = country;
    this.timezone = timezone;
    this.tags = tags;
  }

}
