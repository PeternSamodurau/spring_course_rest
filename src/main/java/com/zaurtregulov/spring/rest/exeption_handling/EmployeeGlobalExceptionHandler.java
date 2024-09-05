package com.zaurtregulov.spring.rest.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeGlobalExceptionHandler {
    @ExceptionHandler //аннотация, которая указывает, что этот метод обрабатывает исключения//
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException e) {  // реагирует на (NoSuchEmployeeException

        EmployeeIncorrectData incorrectData = new EmployeeIncorrectData();

        incorrectData.setInfo(e.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler //аннотация, которая указывает, что этот метод обрабатывает исключения//
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception e) {   // реагирует на любой тип исключений

        EmployeeIncorrectData incorrectData = new EmployeeIncorrectData();

        incorrectData.setInfo(e.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }
}
