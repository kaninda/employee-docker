package com.aka.controller.exception;

public class EmployeeFoundException extends RuntimeException {
    public EmployeeFoundException (){
        super();
    }
    public EmployeeFoundException(String message) {
        super(message);
    }
    public EmployeeFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
