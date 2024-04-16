package com.web3.notification.service;

import java.util.List;

import com.web3.notification.dao.DeviceDao;
import com.web3.notification.model.Device;

public class DeviceService {

  DeviceDao deviceDao = new DeviceDao();
  public List<Device> getAllDevice() {
    return deviceDao.getAllCategory();
  }
}
