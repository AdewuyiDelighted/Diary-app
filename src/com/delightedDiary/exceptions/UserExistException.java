package com.delightedDiary.exceptions;

public class UserExistException extends DiaryAppException {
    public UserExistException(String message){
        super(message);
    }
}