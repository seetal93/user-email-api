package com.qa.user.userapi.util.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String exception){
        super("Id supplied does not exist. Id: " + exception);
    }

}
