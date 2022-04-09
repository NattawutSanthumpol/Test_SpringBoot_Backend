package com.santhumpol.backend_spring_boot.exception;

public class BaseException extends Exception {
    public BaseException(String code) {
        super(code);
    }
}
