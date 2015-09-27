package rest.domain;

import rest.User;

public class GenericResponse {
	
boolean success=false;
String message = "";

public GenericResponse(boolean success, String message) {
	super();
	this.success = success;
	this.message = message;
}

public boolean isSuccess() {
	return success;
}

public String getMessage() {
	return message;
}




}
