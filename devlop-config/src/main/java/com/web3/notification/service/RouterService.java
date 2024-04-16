package com.web3.notification.service;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.web3.notification.exception.BadRequestException;
import com.web3.notification.exception.NotFoundException;
import com.web3.notification.model.Category;
import com.web3.notification.model.Template;
import com.web3.notification.util.Constant;
import com.web3.notification.util.HibernateFactory;
import com.web3.notification.util.Utility;

public class RouterService {

  public APIGatewayProxyResponseEvent routeRequest(APIGatewayProxyRequestEvent input) {
	Map<String, String> headers = new HashMap<>();
	headers.put("Content-Type", "application/json");
	headers.put("X-Custom-Header", "application/json");

	String result = "OK";
	int statusCode = HttpURLConnection.HTTP_OK;

	try {
	  switch (input.getResource()) {
	  case "/template":
		if (Constant.HTTP_METHOD_GET.equals(input.getHttpMethod().toUpperCase())) {
		  result = Utility.toJson(new TemplateService().getAllTemplate());
		} else if (Constant.HTTP_METHOD_DELETE.equals(input.getHttpMethod().toUpperCase())) {

		} else if (Constant.HTTP_METHOD_POST.equals(input.getHttpMethod().toUpperCase())) {
		  result = Utility
			  .toJson(new TemplateService().createNewTemplate(Utility.fromJson(input.getBody(), Template.class)));
		}

		break;
	  case "/template/{id}":

		break;
	  case "/category":
	  if (Constant.HTTP_METHOD_GET.equals(input.getHttpMethod().toUpperCase())) {
      result = Utility.toJson(new CategoryService().getAllCategory());
    }  
	  else if (Constant.HTTP_METHOD_POST.equals(input.getHttpMethod().toUpperCase())) {
      result = Utility.toJson(new CategoryService().createNewCategory(Utility.fromJson(input.getBody(), Category.class)));
    }
	  break;
	  case "/category/{id}":
	    
	    if (Constant.HTTP_METHOD_GET.equals(input.getHttpMethod().toUpperCase())) {
	      result = Utility.toJson(new CategoryService().getCategoryDetailsById(input.getPathParameters()));
	    } else if (Constant.HTTP_METHOD_DELETE.equals(input.getHttpMethod().toUpperCase())) {
	      result = Utility.toJson(new CategoryService().deleteCategoryDetailsById(input.getPathParameters()));
	    } else if (Constant.HTTP_METHOD_PUT.equals(input.getHttpMethod().toUpperCase())) {
	      result = Utility.toJson(new CategoryService().updateAnExistingCategory(input.getPathParameters(),Utility.fromJson(input.getBody(), Category.class)));
	    }
	  }
	} catch (BadRequestException badRequest) {
	  statusCode = HttpURLConnection.HTTP_BAD_REQUEST;
	  result = Utility.toJson(Utility.buildErrorObject(badRequest.getMessage()));
	} catch (NotFoundException notFoundException) {
	  statusCode = HttpURLConnection.HTTP_NOT_FOUND;
	  result = Utility.toJson(Utility.buildErrorObject(notFoundException.getMessage()));
	} catch (Exception e) {
	  statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
	  result = Utility.toJson(Utility.buildErrorObject(e.getMessage()));
	  e.printStackTrace();
	}

	return new APIGatewayProxyResponseEvent().withHeaders(headers).withStatusCode(statusCode).withBody(result);
  }
}
