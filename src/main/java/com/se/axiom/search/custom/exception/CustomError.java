package com.se.axiom.search.custom.exception;

public enum CustomError {
	 INTEGRATION("600", "Error while retrive data from Thirdparty service"),
	 INITIALDATA("601", "Error to load inital data"),
	 MOBILESERACH("603", "Exception happend while filter mobile deice ");
	  private final String code;
	  private final String message;

	  private CustomError(String code, String message) {
	    this.code = code;
	    this.message = message;
	  }

	  public String getMessage() {
	     return message;
	  }

	  public String getCode() {
	     return code;
	  }

	  @Override
	  public String toString() {
	    return code + ": " + message;
	  }
}
