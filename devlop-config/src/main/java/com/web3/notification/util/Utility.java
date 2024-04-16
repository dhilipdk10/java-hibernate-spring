package com.web3.notification.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web3.notification.model.Category;


public class Utility {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static String buildErrorObject(String message) {
		return Map.of("errorMessage", message).toString();
	}

	public static <T> String toJson(T object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String json, Class<T> type) {
		try {
			return OBJECT_MAPPER.readValue(json, type);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T fromJson(String json, TypeReference<T> type) {
		try {
			return OBJECT_MAPPER.readValue(json, type);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
