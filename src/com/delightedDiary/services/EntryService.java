package com.delightedDiary.services;

import com.delightedDiary.data.models.Entry;

import java.util.List;

public interface EntryService {
    void creatEntry(String title,String body,int diaryId);
    List <Entry> findAll ();


}
