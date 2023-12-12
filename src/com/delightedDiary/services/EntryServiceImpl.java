package com.delightedDiary.services;

import com.delightedDiary.data.models.Entry;
import com.delightedDiary.data.repositories.EntryRepository;
import com.delightedDiary.data.repositories.EntryRepositoryImpl;

import java.util.List;

public class EntryServiceImpl implements EntryService {
    EntryRepository entryRepository =  new EntryRepositoryImpl();
    @Override
    public void creatEntry(String title, String body, int diaryId) {
        Entry entry = new Entry();
        entry.setTitle(title);
        entry.setBody(body);
        entry.setDiaryId(diaryId);
        entryRepository.save(entry);
    }

    @Override
    public List<Entry> findAll() {
        return entryRepository.findAll();
    }
}
