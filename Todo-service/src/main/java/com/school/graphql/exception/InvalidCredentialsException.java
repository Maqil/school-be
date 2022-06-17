package com.school.graphql.exception;

public class InvalidCredentialsException extends  Exception {
        public InvalidCredentialsException(String message){
            super(message);
        }
}
