package com.example.rentavehicleagency.auth.exceptions;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException(String message){
        super(message);
    }
}
