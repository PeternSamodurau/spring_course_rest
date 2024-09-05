package com.zaurtregulov.spring.rest.exeption_handling;

public class NoSuchEmployeeException extends RuntimeException {

    public NoSuchEmployeeException(String info) {
        super(info);
    }
}
