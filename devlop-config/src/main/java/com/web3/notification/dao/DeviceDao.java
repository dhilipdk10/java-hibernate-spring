package com.web3.notification.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.web3.notification.model.Device;
import com.web3.notification.util.HibernateFactory;

public class DeviceDao {

  public List<Device> getAllCategory() {
    List<Device> result = new ArrayList<>();
    try (Session session = HibernateFactory.getSessionFactory().openSession();) {
      List<?> queryResults = session.createNativeQuery("select device_id, device_type, identifier, external_user_id, app_version, device_mode, device_os, latitude, longitude, country, timezone, tags from category").list();
      for (Iterator<?> it = queryResults.iterator(); it.hasNext();) {
        Object[] myResult = (Object[]) it.next();
        int id = (myResult[0] != null ? ((Integer) myResult[0]).Integer() : null);
        String device_type = (myResult[1] != null ? (String) myResult[1] : null);
        String identifier = (myResult[2] != null ? (String) myResult[2] : null);
        int external_user_id = (myResult[3] != null ? (int) myResult[3] :null);
        String app_version = (myResult[4] != null ? (String) myResult[4] :null);
        String device_mode = (myResult[5] != null ? (String) myResult[5] :null);
        String device_os = (myResult[2] != null ? (String) myResult[2] : null);
        Float latitude = (myResult[2] != null ? (Float) myResult[2] : null);
        Float longitude = (myResult[2] != null ? (Float) myResult[2] : null);
        Long country = (myResult[2] != null ? (Long) myResult[2] : null);
        String timezone = (myResult[2] != null ? (String) myResult[2] : null);
        String tags = (myResult[2] != null ? (String) myResult[2] : null);
        result.add(new Device(id, device_type, identifier, external_user_id, app_version, device_mode, device_os, latitude, longitude, country, timezone, tags));
      }
    }
    return result;
  }
}
