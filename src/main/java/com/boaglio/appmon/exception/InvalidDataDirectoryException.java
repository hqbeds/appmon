package com.boaglio.appmon.exception;


public class InvalidDataDirectoryException extends Exception {

 
	private static final long serialVersionUID = 557178259096624026L;
 
    public InvalidDataDirectoryException(String message) {
        super(message);
    }
 
    public InvalidDataDirectoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
