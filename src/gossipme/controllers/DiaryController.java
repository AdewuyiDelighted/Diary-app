package gossipme.controllers;

import gossipme.exceptions.DiaryAppException;
import gossipme.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class DiaryController {
    @Autowired
    DiaryService diaryService;
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
    public String creatEntry(@RequestParam String username, @RequestParam String title, @RequestParam String body) {
   // public String creatEntry(@RequestParam String username, @RequestParam String title, @RequestParam String body){
        try {
            diaryService.writeOn(username, title, body);
            return "entry saved";
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/entry/{username}")
    public Object findEntryBelongingTo(@PathVariable("username") String username) {
        try {
            return diaryService.findEntriesBelongingTo(username);
        } catch (DiaryAppException ex) {
            return ex.getMessage();
        }
    }


}
