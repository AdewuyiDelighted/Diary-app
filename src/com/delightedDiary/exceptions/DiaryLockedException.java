package com.delightedDiary.exceptions;

public class DiaryLockedException extends DiaryAppException {
    public DiaryLockedException(){
        super("Diary locked");
    }
}
