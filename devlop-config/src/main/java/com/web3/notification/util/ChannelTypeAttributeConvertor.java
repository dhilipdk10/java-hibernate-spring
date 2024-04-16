package com.web3.notification.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web3.notification.enums.ChannelEnum;

public class ChannelTypeAttributeConvertor implements AttributeConverter<List<ChannelEnum>, String> {

  @Override
  public String convertToDatabaseColumn(List<ChannelEnum> attribute) {
	if (attribute == null || attribute.isEmpty()) {
	  return "[]";
	}
	try {
	  ObjectMapper objectMapper = new ObjectMapper();
	  return objectMapper.writeValueAsString(attribute);
	} catch (IOException e) {
	  // TODO
	}
	return "[]";
  }

  @Override
  public List<ChannelEnum> convertToEntityAttribute(String dbData) {
	if (dbData == null) {
	  return new ArrayList<>();
	}
	try {
	  ObjectMapper objectMapper = new ObjectMapper();
	  return objectMapper.readValue(dbData, new TypeReference<List<ChannelEnum>>() {
	  });
	} catch (IOException e) {
	  // TODO
	}
	return new ArrayList<>();
  }

}
