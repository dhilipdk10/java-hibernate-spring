package com.web3.notification.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class Device_Tag {

  @Column(name = "id")
  private int id;
  
  @Column(name = "field")
  private String field;
  
  @Column(name = "value")
  private String value;
  
  @ManyToOne
  @Column(name = "device_id")
  private Device device;
  
  public Device_Tag () {
    
  }

  public Device_Tag(int id, String field, String value, Device device) {
    super();
    this.id = id;
    this.field = field;
    this.value = value;
    this.device = device;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getField() {
    return this.field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Device getDevice() {
    return this.device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }
  
}
