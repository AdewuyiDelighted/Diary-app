package services;

import data.models.Entry;
import data.repositories.EntryRepository;
import data.repositories.EntryRepositoryImpl;

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
