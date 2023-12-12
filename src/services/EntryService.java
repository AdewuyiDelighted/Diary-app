package services;

import data.models.Entry;

import java.util.List;

public interface EntryService {
    void creatEntry(String title,String body,int diaryId);
    List <Entry> findAll ();


}
