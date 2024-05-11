package com.aka.controller;

import com.aka.controller.exception.EmployeeFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeFoundException.class)
    protected ProblemDetail  handleNotfound(EmployeeFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setTitle("Employee cannot be found");
        return problemDetail;
    }
}
