package controllers;

import data.models.Entry;
import exceptions.DiaryAppException;
import services.DiaryService;
import services.DiaryServiceImpl;

import java.util.List;

public class DiaryController {
    DiaryService diaryService = new DiaryServiceImpl();

    public String register(String username, String password) {
        try {
            diaryService.register(username, password);
            return "Account created";
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }

    public String login(String username, String password) {
        try {
            diaryService.login(username, password);
            return "Diary unlocked";
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }

    public String creatEntry(String username, String title, String body) {
        try {
            diaryService.writeOn(username, title, body);
            return "entry saved";
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }
    public Object findEntryBelongingTo(String username){
        try{
            return  diaryService.findEntriesBelongingTo(username);
        }catch(DiaryAppException ex){
            return  ex.getMessage();
        }
    }



}
