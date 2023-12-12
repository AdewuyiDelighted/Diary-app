package com.delightedDiary.controllers;

import com.delightedDiary.exceptions.DiaryAppException;
import com.delightedDiary.services.DiaryService;
import com.delightedDiary.services.DiaryServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController

public class DiaryController {
    DiaryService diaryService = new DiaryServiceImpl();

    @GetMapping("/register/{username}/{password}")
    public String register(@PathVariable("username") String username, @PathVariable("password") String password) {
        try {
            diaryService.register(username, password);
            return "Account created";
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
        try {
            diaryService.login(username, password);
            return "Diary unlocked";
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }

    @PostMapping("/entry")
    public String creatEntry(@RequestParam("username") String username, @RequestParam("title") String title, @RequestParam("body") String body) {
        try {
            diaryService.writeOn(username, title, body);
            return "entry saved";
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/entry/{username}")
    public Object findEntryBelongingTo(@PathVariable
                                           String username) {
        try {
            return diaryService.findEntriesBelongingTo(username);
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }


}
